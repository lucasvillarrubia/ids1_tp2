package ar.uba.fi.ingsoft1.todo_template.partido;


import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;
import java.util.function.LongFunction;

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

        partidoRepository.save(newPartido);
        //agregar PARTIDO a historial de user creator TODO

        return newPartidoDTO;
    }

    boolean validatePartidoCreationInputs(PartidoDTO partidoDTO){
        //verif valid userId
        // existe este user.....

        //verif cancha dispo (hace falta el id a menos q el check de dispo devuelva error  en caso de cancha inexistente)

        //verif valid team o user ¿participationTypeService?

        return true;
    }

    public void deletePartido(Long id) {
        Partido partido = partidoRepository.findById(id).orElse(null);

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

        if (partido == null || !partido.esOrganizador(1L)) {
            return null;
        }

        Partido saved;
        try {
            saved = partidoRepository.save(partidoCreateDTO.asPartido());
        } catch (MethodArgumentNotValidException e) {
            throw new RuntimeException(e);
        }
        return new PartidoDTO(saved);
    }

    Page<PartidoDTO> getPartidos(Pageable pageable) {
        return partidoRepository.findAll(pageable).map(PartidoDTO::new);
    }




}
