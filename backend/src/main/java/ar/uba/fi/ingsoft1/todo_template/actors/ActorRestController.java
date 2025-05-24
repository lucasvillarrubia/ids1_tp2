package ar.uba.fi.ingsoft1.todo_template.actors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
@Tag(name = "4 - Actors")
class ActorRestController {

//    private final ActorService actorService;
//
//    @Autowired
//    ActorRestController(ActorService actorService) {
//        this.actorService = actorService;
//    }
//
//    @GetMapping(produces = "application/json")
//    @Operation(summary = "Get a list of actors")
//    @ResponseStatus(HttpStatus.OK)
//    Page<ActorDTO> getActors(
//            @Valid @ParameterObject Pageable pageable
//    ) throws MethodArgumentNotValidException {
//        return actorService.getActors(pageable);
//    }
//
//    @GetMapping(value = "/{id}", produces = "application/json")
//    @Operation(summary = "Get a actor by its id")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiResponse(responseCode = "404", description = "actor not found", content = @Content)
//    ActorDTO getActor(
//            @Valid @PathVariable @Positive long id
//    ) throws MethodArgumentNotValidException, ItemNotFoundException {
//        return actorService.getActor(id);
//    }
//
//    @PostMapping(produces = "application/json")
//    @Operation(summary = "Create a new actor")
//    @ResponseStatus(HttpStatus.CREATED)
//    ActorDTO createActor(
//            @Valid @RequestBody ActorCreateDTO actorCreate
//    ) throws MethodArgumentNotValidException, ItemNotFoundException {
//        return actorService.createActor(actorCreate);
//    }
//
//    @PutMapping(value = "/{id}", produces = "application/json")
//    @Operation(summary = "Update a actor")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiResponse(responseCode = "404", description = "actor not found", content = @Content)
//    ResponseEntity<ActorDTO> putActor(
//            @Valid @PathVariable @Positive long id,
//            @Valid @RequestBody ActorCreateDTO actorCreate
//    ) throws MethodArgumentNotValidException, ItemNotFoundException {
//        return ResponseEntity.of(actorService.updateActor(id, actorCreate));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @Operation(summary = "Delete an actor")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void deleteActor(
//            @Valid @PathVariable @Positive long id
//    ) throws MethodArgumentNotValidException {
//        actorService.deleteActor(id);
//    }

    private final ActorService actorService;

    public ActorRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new actor")
    @ApiResponse(responseCode = "201", description = "Actor created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<ActorDTO> createActor(
            @Valid @RequestBody ActorCreateDTO actorCreateDTO
    ) {
        ActorDTO created = this.actorService.createActor(actorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a actor by its id")
    @ApiResponse(responseCode = "200", description = "Actor updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<ActorDTO> updateActor(
            @PathVariable @Positive long id,
            @Valid @RequestBody ActorCreateDTO actorCreateDTO
    ) {
        ActorDTO updated = actorService.updateActor(id, actorCreateDTO);

        if (updated != null) {
            return ResponseEntity.ok(updated);  // Return updated actor with 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if actor was not found
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a actor by its id")
    @ApiResponse(responseCode = "200", description = "Actor deleted successfully")
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<Void> deleteActor(@PathVariable @Positive long id) {
        boolean deleted = actorService.deleteActor(id);

        if (deleted) {
            return ResponseEntity.ok().build();  // Return 200 OK if deletion was successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if actor was not found
        }
    }

}
