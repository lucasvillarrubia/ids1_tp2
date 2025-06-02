package ar.uba.fi.ingsoft1.todo_template.match;



import ar.uba.fi.ingsoft1.todo_template.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.Open;
import ar.uba.fi.ingsoft1.todo_template.match.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
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

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserService userService;

    /*
    @Autowired
    CanchaService canchaService;

    @Autowired
    TeamService canchaService;
     */

    public MatchDTO createMatch(MatchCreateDTO MatchCreateDTO) throws MethodArgumentNotValidException {
        Match newMatch = MatchCreateDTO.asMatch();
        MatchDTO newMatchDTO = new MatchDTO(newMatch);
        // TODO: Agregar al jugador que crea el Match al Match una vez creado

        if (!validateMatchCreationInputs(newMatchDTO)){
            throw new UsernameNotFoundException("Invalid inputs"); // despues cambiar por errores mas representativos
        }

        Match savedMatch = matchRepository.save(newMatch);
        //agregar Match a historial de user creator TODO
        return new MatchDTO(savedMatch);
    }

    boolean validateMatchCreationInputs(MatchDTO matchDTO){
        //verif valid userId
        // existe este user.....

        //verif cancha dispo (hace falta el id a menos q el check de dispo devuelva error  en caso de cancha inexistente)

        //verif valid team o user ¿participationTypeService?

        return true;
    }

    public void deleteMatch(Long id) {
        //Match Match = MatchRepository.findById(id).orElse(null);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String currentUsername = authentication.getName();
        // validar user id
        //if (Match == null || !Match.esOrganizador(currentUsrId)){
        //    return null;
        //}
        matchRepository.deleteById(id);
    }

    public MatchDTO updateMatch(Long id, MatchCreateDTO matchCreateDTO) {
        Match Match = matchRepository.findById(id).orElse(null);

        //if (Match == null || !Match.esOrganizador(actuaUserId)) {
        if (Match == null) {
            return null;
        }

        Match saved;
        try {
            Match updatedMatch = matchCreateDTO.asMatch();
            updatedMatch.setId(id);
            saved = matchRepository.save(updatedMatch);
        } catch (MethodArgumentNotValidException e) {
            throw new RuntimeException(e);
        }
        return new MatchDTO(saved);
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

    public Page<MatchDTO> getAllAvailableMatches(@Valid Pageable pageable) {
        return matchRepository.findAllWithOpenParticipationNative(pageable).map(MatchDTO::new);
    }

    public MatchDTO joinMatch(Long id) {
        Long userId = getUserId();
        Match Match = getMatchById(id);
        if (!Match.canJoin()){
            throw new RuntimeException("Can't join match");
        }
        ParticipationType participationType = Match.getParticipationType();
        Open openParticipation = (Open) participationType;
        if (openParticipation.getPlayerCount() >= openParticipation.getMaxPlayersCount()){
            throw new RuntimeException("Match is already full!");
        }
        HashSet<Long> participationIds = openParticipation.getPlayerIds();

        if (participationIds.contains(userId)){
            throw new RuntimeException("User already registered");
        }

        participationIds.add(userId);
        matchRepository.save(Match);

        return new MatchDTO(Match);
    }

    public MatchDTO leaveMatch(Long id){
        // TODO: Verificar si el usuario a abandonar es el anfitrion
        Long userId = getUserId();
        Match Match = getMatchById(id);
        if (!Match.canLeave()){
            throw new RuntimeException("Can't leave match");
        }
        ParticipationType participationType = Match.getParticipationType();
        Open openParticipation = (Open) participationType;
        HashSet<Long> participationIds = openParticipation.getPlayerIds();

        if (!participationIds.contains(userId)){
            throw new RuntimeException("User is no longer in the match");
        }
        participationIds.remove(userId);
        matchRepository.save(Match);

        return new MatchDTO(Match);
    }

    private Long getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        return userService.getUserId(email);
    }

    private Match getMatchById(Long id){
        Optional<Match> MatchFound = matchRepository.findById(id);
        if (MatchFound.isEmpty()) {
            throw new RuntimeException("Match not found");
        }
        return MatchFound.get();
    }

}
