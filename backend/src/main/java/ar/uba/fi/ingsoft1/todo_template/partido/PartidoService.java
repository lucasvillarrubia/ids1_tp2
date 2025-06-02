package ar.uba.fi.ingsoft1.todo_template.partido;



import ar.uba.fi.ingsoft1.todo_template.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.todo_template.partido.participationType.Open;
import ar.uba.fi.ingsoft1.todo_template.partido.participationType.ParticipationType;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;

@Service
@Transactional
public class PartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    UserService userService;

    /*
    @Autowired
    CanchaService canchaService;

    @Autowired
    TeamService canchaService;
     */

    public PartidoDTO createPartido(PartidoCreateDTO partidoCreateDTO) throws MethodArgumentNotValidException {
        Partido newPartido = partidoCreateDTO.asPartido();
        PartidoDTO newPartidoDTO = new PartidoDTO(newPartido);

        if (!validatePartidoCreationInputs(newPartidoDTO)){
            throw new UsernameNotFoundException("Invalid inputs"); // despues cambiar por errores mas representativos
        }

        Partido savedPartido = partidoRepository.save(newPartido);
        //agregar PARTIDO a historial de user creator TODO
        return new PartidoDTO(savedPartido);
    }

    boolean validatePartidoCreationInputs(PartidoDTO partidoDTO){
        //verif valid userId
        // existe este user.....

        //verif cancha dispo (hace falta el id a menos q el check de dispo devuelva error  en caso de cancha inexistente)

        //verif valid team o user ¿participationTypeService?

        return true;
    }

    public void deletePartido(Long id) {
        //Partido partido = partidoRepository.findById(id).orElse(null);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String currentUsername = authentication.getName();
        // validar user id
        //if (partido == null || !partido.esOrganizador(currentUsrId)){
        //    return null;
        //}
        partidoRepository.deleteById(id);
    }

    public PartidoDTO updatePartido(Long id, PartidoCreateDTO partidoCreateDTO ) {
        Partido partido = partidoRepository.findById(id).orElse(null);

        //if (partido == null || !partido.esOrganizador(actuaUserId)) {
        if (partido == null) {
            return null;
        }

        Partido saved;
        try {
            Partido updatedPartido = partidoCreateDTO.asPartido();
            updatedPartido.setId(id);
            saved = partidoRepository.save(updatedPartido);
        } catch (MethodArgumentNotValidException e) {
            throw new RuntimeException(e);
        }
        return new PartidoDTO(saved);
    }

    PartidoDTO getPartido(Long id) throws MethodArgumentNotValidException {
        Optional<Partido> partidoFound = partidoRepository.findById(id);
        PartidoDTO partidoFoundDTO;
        try {
            partidoFoundDTO = new PartidoDTO(partidoFound.get());
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

        return partidoFoundDTO;
    }

    public Page<PartidoDTO> getAllAvailablePartidos(@Valid Pageable pageable) {
        return partidoRepository.findAllWithOpenParticipationNative(pageable).map(PartidoDTO::new);
    }

    public PartidoDTO joinMatch(Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null)
            throw new RuntimeException("Authentication is null");
        Object principal = authentication.getPrincipal();
        JwtUserDetails userDetails = (JwtUserDetails) principal;
        String email = userDetails.username();
        User user = userService.getUser(email);

        Optional<Partido> partidoFound = partidoRepository.findById(id);
        if (partidoFound.isEmpty()) {
            throw new RuntimeException("Partido not found");
        }
        Partido partido = partidoFound.get();
        ParticipationType participationType = partido.getParticipationType();
        Open openParticipation = (Open) participationType;
        HashSet<Long> participationIds = openParticipation.getPlayerIds();

        if (participationIds.contains(user.getId())){
            throw new RuntimeException("User already registered");
        }

        participationIds.add(user.getId());
        openParticipation.increasePlayerCount();
        partidoRepository.save(partido);

        return new PartidoDTO(partido);
    }



}
