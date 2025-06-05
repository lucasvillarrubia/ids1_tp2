package ar.uba.fi.ingsoft1.todo_template.match;



import ar.uba.fi.ingsoft1.todo_template.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.Open;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationTypeService;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import ar.uba.fi.ingsoft1.todo_template.equipo.EquipoService;
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
    private final FieldService fieldService;
    private final ParticipationTypeService participationTypeService;

    public MatchService(MatchRepository matchRepository, UserService userService,FieldService fieldService, ParticipationTypeService participationTypeService) {
        this.matchRepository = matchRepository;
        this.userService = userService;
        this.fieldService = fieldService;
        this.participationTypeService = participationTypeService;
    }

    public MatchDTO createMatch(MatchCreateDTO matchCreateDTO) {
        Match newMatch = buildMatch(matchCreateDTO);
        Match savedMatch = matchRepository.save(newMatch);
        return new MatchDTO(savedMatch);
    }

    private Match buildMatch(MatchCreateDTO matchCreateDTO) {
        Field field = getField(matchCreateDTO.getFieldId());

        ParticipationType partType = participationTypeService.buildFromDTO(matchCreateDTO.getParticipationType());

        Match newMatch = matchCreateDTO.asMatch(userService.getUserById(getUserEmail()),field,partType);

        if (!validateMatchCreationInputs(new MatchDTO(newMatch))){
            throw new UsernameNotFoundException("Invalid inputs"); // despues cambiar por errores mas representativos
        }
        return newMatch;
    }

    boolean validateMatchCreationInputs(MatchDTO matchDTO){
        //verif valid userId
        // existe este user.....

        //verif cancha dispo (hace falta el id a menos q el check de dispo devuelva error  en caso de cancha inexistente)

        return true;
    }

    public void deleteMatch(Long id) {
        Match match = matchRepository.findById(id).orElse(null);

        if (match != null && match.isOrganizer(userService.getUserById(getUserEmail()))){
            matchRepository.deleteById(id);
        }
    }

    public MatchDTO updateMatch(Long id, MatchCreateDTO matchCreateDTO) {
        Match existingMatch = matchRepository.findById(id).orElse(null);

        if (existingMatch != null && !existingMatch.isOrganizer(userService.getUserById(getUserEmail()))){
            throw new RuntimeException("Inexistent match or permissions denied");
        }

        Match match = buildMatch(matchCreateDTO);
        match.setId(id);
        participationTypeService.updateParticipationType(existingMatch.getParticipationType(),match.getParticipationType());
        Match savedMatch = matchRepository.save(match);
        return new MatchDTO(savedMatch);
    }

    MatchDTO getMatch(Long id) throws MethodArgumentNotValidException {
        Optional<Match> MatchFound = matchRepository.findById(id);
        MatchDTO MatchFoundDTO;
        try {
            MatchFoundDTO = new MatchDTO(MatchFound.get());
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

        return MatchFoundDTO;
    }

    public MatchDTO joinMatch(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);

        if (!match.join(userService.getUser(email))){
            throw new RuntimeException("Can't join match");
        }

        Match savedMatch = matchRepository.save(match);
        return new MatchDTO(savedMatch);
    }

    public void leaveMatch(Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        Match match = getMatchById(id);

        if (!match.leaveMatch(userService.getUser(email))){
            throw new RuntimeException("Can't leave match");
        }

        matchRepository.save(match);
    }

    private Match getMatchById(Long id){
        Optional<Match> matchFound = matchRepository.findById(id);
        if (matchFound.isEmpty()) {
            throw new RuntimeException("Match not found");
        }
        //
        return matchFound.get();
    }

    public Page<MatchDTO> getSelfOrganizedMatches(@Valid Pageable pageable){
        return matchRepository.findByOrganizer(pageable,userService.getUserById(getUserEmail())).map(MatchDTO::new);
    }

    public Page<MatchDTO> getMatchesActualPlayerParticipatesIn(@Valid Pageable pageable){
        return matchRepository.findAllMatchesUserPlaysIn(pageable,userService.getUserId(getUserEmail())).map(MatchDTO::new);
    }

    public Page<MatchDTO> getAllAvailableMatches(@Valid Pageable pageable) {
        return matchRepository.findAllWithOpenParticipation(pageable).map(MatchDTO::new);
    }

    private Field getField(Long fieldId) {
        Field field;
        try {
            field = fieldService.getFieldById(fieldId).asField();
        } catch (MethodArgumentNotValidException e) {
            throw new RuntimeException(e);
        }
        return field;
    }

    // ver si se puede meter en otro file con otras funciones para conseguir data del contexto actual del usuario
    private String getUserEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        return userDetails.username();
    }

}
