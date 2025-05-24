package ar.uba.fi.ingsoft1.todo_template.actors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
class ActorService {

//    private final ActorRepository actorRepository;
//    private final MovieRepository movieRepository;
//
//    ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
//        this.actorRepository = actorRepository;
//        this.movieRepository = movieRepository;
//    }
//
//    Page<ActorDTO> getActors(Pageable pageable) {
//        return actorRepository.findAll(pageable).map(ActorDTO::new);
//    }
//
//    ActorDTO getActor(long id) throws ItemNotFoundException {
//        var actor = actorRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("actor", id));
//        return new ActorDTO(actor);
//    }
//
//    ActorDTO createActor(ActorCreateDTO actorCreate) throws ItemNotFoundException {
//        return new ActorDTO(actorRepository.save(actorCreate.asActor(movieRepository::findById)));
//    }
//
//    Optional<ActorDTO> updateActor(long id, ActorCreateDTO actorCreate) throws ItemNotFoundException {
//        if (!actorRepository.existsById(id)) {
//            return Optional.empty();
//        }
//        var saved = actorRepository.save(actorCreate.asActor(id, movieRepository::findById));
//        return Optional.of(new ActorDTO(saved));
//    }
//
//    void deleteActor(long id) {
//        actorRepository.deleteById(id);
//    }

    final private Map<Long, ActorDTO> actors;

    public ActorService() {
        this.actors = new HashMap<>();

        Actor matthew = new Actor(1L, "Matthew McConaughey");
        Actor anne = new Actor(2L, "Anne Hathaway");
        Actor keanu = new Actor(3L, "Keanu Reeves");
        Actor carrie = new Actor(4L, "Carrie-Anne Moss");
        Actor leonardo = new Actor(5L, "Leonardo DiCaprio");
        Actor brad = new Actor(6L, "Brad Pitt");

        this.actors.put(matthew.getId(), new ActorDTO(matthew));
        this.actors.put(anne.getId(), new ActorDTO(anne));
        this.actors.put(keanu.getId(), new ActorDTO(keanu));
        this.actors.put(carrie.getId(), new ActorDTO(carrie));
        this.actors.put(leonardo.getId(), new ActorDTO(leonardo));
        this.actors.put(brad.getId(), new ActorDTO(brad));
    }

    ActorDTO getActor(long id) {
        return this.actors.get(id);
    }

    public ActorDTO updateActor(long id, ActorCreateDTO actorCreateDTO) {
        if (this.actors.containsKey(id)) {
            this.actors.put(id, new ActorDTO(actorCreateDTO.asActor(id)));
        }
        return null;
    }

    boolean deleteActor(long id) {
        if (this.actors.containsKey(id)) {
            this.actors.remove(id);
            return true;
        }
        return false;
    }

    public ActorDTO createActor(ActorCreateDTO dto) {
        String name = "Terror";
        return new ActorDTO(5L, name);
    }

}
