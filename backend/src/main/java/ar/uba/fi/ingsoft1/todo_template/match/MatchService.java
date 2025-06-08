package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import ar.uba.fi.ingsoft1.todo_template.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.field.FieldRepository;
import ar.uba.fi.ingsoft1.todo_template.match.matchOrganizer.MatchOrganizerService;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.Open;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeService;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import ar.uba.fi.ingsoft1.todo_template.field.FieldService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;

@Service
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserService userService;
    // private final FieldService fieldService;
    private final FieldRepository fieldRepository;
    private final ParticipationTypeService participationTypeService;
    private final MatchOrganizerService matchOrganizerService;

    public MatchService(MatchRepository matchRepository, UserService userService, FieldRepository fieldRepository,
            ParticipationTypeService participationTypeService, MatchOrganizerService matchOrganizerService) {
        this.matchRepository = matchRepository;
        this.userService = userService;
        this.fieldRepository = fieldRepository;
        this.participationTypeService = participationTypeService;
        this.matchOrganizerService = matchOrganizerService;
    }

    public MatchDTO createMatch(MatchCreateDTO matchCreateDTO) {
        Match newMatch = buildMatch(matchCreateDTO);
        Match savedMatch = matchRepository.save(newMatch);
        matchOrganizerService.create();
        return new MatchDTO(savedMatch);
    }

    private Match buildMatch(MatchCreateDTO matchCreateDTO) {
        Field field = getField(matchCreateDTO.getFieldId());

        ParticipationType partType = participationTypeService.buildFromDTO(matchCreateDTO.getParticipationType());

        Match newMatch = matchCreateDTO.asMatch(userService.getUserByEmail(getUserEmail()), field, partType);

        if (!validateMatchCreationInputs(new MatchDTO(newMatch))) {
            throw new UsernameNotFoundException("Invalid inputs"); // despues cambiar por errores mas representativos
        }
        return newMatch;
    }

    boolean validateMatchCreationInputs(MatchDTO matchDTO) {
        // verif valid userId
        // existe este user.....

        // verif cancha dispo (hace falta el id a menos q el check de dispo devuelva
        // error en caso de cancha inexistente)

        return true;
    }

    public void deleteMatch(Long id) {
        Match match = matchRepository.findById(id).orElse(null);

        if (match != null && match.isOrganizer(userService.getUserByEmail(getUserEmail()))) {
            matchRepository.deleteById(id);
        }
    }

    public MatchDTO updateMatch(Long id, MatchCreateDTO matchCreateDTO) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));

        if (!existingMatch.isOrganizer(userService.getUserByEmail(getUserEmail()))) {
            throw new InvalidActionException("Permissions denied");
        }

        Match match = buildMatch(matchCreateDTO);
        match.setId(id);
        participationTypeService.updateParticipationType(existingMatch.getParticipationType(),
                match.getParticipationType());
        Match savedMatch = matchRepository.save(match);
        return new MatchDTO(savedMatch);
    }

    MatchDTO getMatch(Long id) {
        Optional<Match> MatchFound = matchRepository.findById(id);
        MatchDTO MatchFoundDTO;
        try {
            MatchFoundDTO = new MatchDTO(MatchFound.get());
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }

        return MatchFoundDTO;
    }

    public MatchDTO joinMatch(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);
        User user = userService.getUserByEmail(email);

        if (!match.join(user)) {
            throw new InvalidActionException("Can't join match");
        }

        Match savedMatch = matchRepository.save(match);
        matchOrganizerService.addPlayer(match.getId(), user.getId());
        return new MatchDTO(savedMatch);
    }

    public void leaveMatch(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);
        User user = userService.getUserByEmail(email);

        if (!match.leaveMatch(user)) {
            throw new InvalidActionException("Can't leave match");
        }
        matchOrganizerService.removePlayer(match.getId(), user.getId());
        matchRepository.save(match);
    }

    public void closeMatch(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);

        if (!match.isOrganizer(userService.getUserByEmail(email))) {
            throw new InvalidActionException("You don't have permissions to close the match");
        }
        ParticipationType participationType = match.getParticipationType();
        if (participationType.getPlayerCount() < participationType.getMinPlayersCount()) {
            throw new InvalidActionException("The match does not have enough players to close");

        }
        match.close();
        matchRepository.save(match);
    }

    // Hay que validar que el usuario tenga permiso?
    // Front puede hacer que algo no aparezca si no tiene permiso?
    public MatchDTO startMatch(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);

        if (!match.isOrganizer(userService.getUserByEmail(email))) {
            throw new InvalidActionException("You don't have permissions to start the match");
        }
        matchOrganizerService.finish(match.getId());
        match.start();
        return new MatchDTO(match);
    }

    private Match getMatchById(Long id) {
        Optional<Match> matchFound = matchRepository.findById(id);
        if (matchFound.isEmpty()) {
            throw new EntityNotFoundException("Match not found");
        }
        return matchFound.get();
    }

    public Page<MatchDTO> getSelfOrganizedMatches(@Valid Pageable pageable) {
        return matchRepository.findByOrganizer(pageable, userService.getUserByEmail(getUserEmail())).map(MatchDTO::new);
    }

    public Page<MatchDTO> getMatchesActualPlayerParticipatesIn(@Valid Pageable pageable) {
        return matchRepository.findAllMatchesUserPlaysIn(pageable, userService.getUserByEmail(getUserEmail()).getId())
                .map(MatchDTO::new);
    }

    public Page<MatchDTO> getAllAvailableMatches(@Valid Pageable pageable) {
        return matchRepository.findAllWithOpenParticipation(pageable).map(MatchDTO::new);
    }

    private Field getField(Long fieldId) {
        Field field;
        // no tenía sentido que fuera un método del fieldService, ya que no se usa
        // en ningún otro lugar y es raro crear un Field desde un FieldDTO en vez del CreateDTO
        try {
            field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        } catch (EntityNotFoundException e) {
        //} catch (MethodArgumentNotValidException e) {
            throw new EntityNotFoundException(e);
        }
        return field;
    }

    // ver si se puede meter en otro file con otras funciones para conseguir data
    // del contexto actual del usuario
    private String getUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        return userDetails.username();
    }

}
