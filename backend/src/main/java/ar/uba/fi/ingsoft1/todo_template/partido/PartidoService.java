package ar.uba.fi.ingsoft1.todo_template.partido;

import ar.uba.fi.ingsoft1.todo_template.actors.Actor;
import ar.uba.fi.ingsoft1.todo_template.actors.ActorCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.categories.Category;
import ar.uba.fi.ingsoft1.todo_template.categories.CategoryCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.movies.Movie;
import ar.uba.fi.ingsoft1.todo_template.movies.MovieCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.movies.MovieDTO;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PartidoService {

    @Autowired
    PartidosRepository repo;

    // mmmm.... hace ruido pero como se verifica que los equipos/jugadores dados son validos????
    //private final TeamsRepository projectRepository;
    //private final TeamsRepository projectRepository;

    public Partido createPartido(Partido partidoDTO) {
        //Partido new_partido = new Partido(partidoDTO);
        repo.save(partidoDTO);
        return partidoDTO;
    }

    public Partido deletePartido(Long id, String currentUsername) {
        Partido partido = repo.findById(id).orElse(null);

        if (partido == null || !partido.esOrganizador(currentUsername)){
            return null;
        }
        repo.deleteById(id);
        return partido;
    }

    public Partido updatePartido(Long id, String currentUsername, Partido updatedPartido) {
        Partido partido = repo.findById(id).orElse(null);

        if (partido == null || !partido.esOrganizador(currentUsername)){
            return null;
        }

        repo.save(updatedPartido);
        return updatedPartido;
    }

    //metodo de prueba --- ELIMINAR DESPUES
    public List<Partido> getAll() {
        return repo.findAll();    }
}
