package ar.uba.fi.ingsoft1.todo_template.partido;


import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import ar.uba.fi.ingsoft1.todo_template.projects.ProjectDTO;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //se nita verificar que los equipos/jugadores dados son validos
    //private final TeamRepository teamRepository;
    //private final TeamRepository projectRepository;

    public PartidoDTO createPartido(PartidoCreateDTO partidoCreateDTO) throws MethodArgumentNotValidException {
        return new PartidoDTO(partidoRepository.save(partidoCreateDTO.asPartido()));
    }

    public Partido deletePartido(Long id, String currentUsername) {
        Partido partido = partidoRepository.findById(id).orElse(null);

        if (partido == null || !partido.esOrganizador(currentUsername)){
            return null;
        }
        partidoRepository.deleteById(id);
        return partido;
    }

    public PartidoDTO updatePartido(Long id, PartidoCreateDTO partidoCreateDTO ) {
        Partido partido = partidoRepository.findById(id).orElse(null);

        if (partido == null || !partido.esOrganizador("cambiar por id")) {
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

    //metodo de prueba --- ELIMINAR DESPUES
    public List<Partido> getAll() {
        return partidoRepository.findAll();    }
}
