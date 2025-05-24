package ar.uba.fi.ingsoft1.todo_template.movies;

import ar.uba.fi.ingsoft1.todo_template.actors.ActorCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.categories.CategoryCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.ratings.RatingDTO;
import ar.uba.fi.ingsoft1.todo_template.ratings.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "3 - Movies")
public class MovieRestController {

    private final MovieService movieService;
    private final RatingService ratingService;

    public MovieRestController(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a movie by its id")
    @ApiResponse(responseCode = "200", description = "Movie found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    public ResponseEntity<MovieDTO> getMovie(
            @Valid @PathVariable @Positive long id
    ) {
        MovieDTO movie = this.movieService.getMovie(id);
        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/actors/{actorId}", produces = "application/json")
    @Operation(summary = "Get movies by actor id")
    @ApiResponse(responseCode = "200", description = "Movies found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "204", description = "No movies found", content = @Content)
    public ResponseEntity<List<MovieDTO>> getMovieByActor(@Valid @PathVariable @Positive long actorId) {
        List<MovieDTO> movies = this.movieService.getMovieByActor(actorId);
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/categories/{categoryId}", produces = "application/json")
    @Operation(summary = "Get movies by category id")
    @ApiResponse(responseCode = "200", description = "Movies found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "204", description = "No movies found", content = @Content)
    public ResponseEntity<List<MovieDTO>> getMovieByCategory(@Valid @PathVariable @Positive long categoryId) {
        List<MovieDTO> movies = this.movieService.getMovieByCategory(categoryId);
        return movies.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}/ratings", produces = "application/json")
    @Operation(summary = "Get movie ratings")
    @ApiResponse(responseCode = "200", description = "Movie ratings found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "204", description = "No ratings found", content = @Content)
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    public ResponseEntity<List<RatingDTO>> getMovieRatings(@PathVariable("id") long movieId) {
        List<RatingDTO> ratings = ratingService.getRatingsByMovie(movieId);
        return ratings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(ratings);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new movie")
    @ApiResponse(responseCode = "201", description = "Movie created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> createMovie(
            @Valid @RequestBody MovieCreateDTO movieCreateDTO
    ) {
        MovieDTO createdMovie = this.movieService.createMovie(movieCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a movie by its id")
    @ApiResponse(responseCode = "200", description = "Movie updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Movie not found")
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> updateMovie(
            @PathVariable @Positive long id,
            @Valid @RequestBody MovieCreateDTO movieCreateDTO
    ) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movieCreateDTO);

        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping(value = "/{id}/actors", produces = "application/json")
    @Operation(summary = "Update movie actors")
    @ApiResponse(responseCode = "200", description = "Actors updated", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    @ApiResponse(responseCode = "400", description = "Invalid actor data", content = @Content)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> updateMovieActors(
            @PathVariable("id") long movieId,
            @RequestBody List<ActorCreateDTO> actors
    ) {
        MovieDTO updatedMovie = movieService.replaceMovieActors(movieId, actors);
        return updatedMovie != null
                ? ResponseEntity.ok(updatedMovie)
                : ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{id}/categories", produces = "application/json")
    @Operation(summary = "Update movie categories")
    @ApiResponse(responseCode = "200", description = "Categories updated", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    @ApiResponse(responseCode = "400", description = "Invalid category data", content = @Content)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> updateMovieCategories(
            @PathVariable("id") long movieId,
            @RequestBody List<CategoryCreateDTO> categories
    ) {
        MovieDTO updatedMovie = movieService.replaceMovieCategories(movieId, categories);
        return updatedMovie != null
                ? ResponseEntity.ok(updatedMovie)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a movie by its id")
    @ApiResponse(responseCode = "200", description = "Movie deleted successfully")
    @ApiResponse(responseCode = "404", description = "Movie not found")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable @Positive long id) {
        boolean deleted = movieService.deleteMovie(id);

        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

