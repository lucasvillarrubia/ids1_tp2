package ar.uba.fi.ingsoft1.todo_template.ratings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@Tag(name = "6 - Ratings")
public class RatingRestController {

    private final RatingService ratingService;

    public RatingRestController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a rating by its id")
    @ApiResponse(responseCode = "200", description = "Rating found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Rating not found", content = @Content)
    public ResponseEntity<RatingDTO> getRating(
            @Valid @PathVariable @Positive long id
    ) {
        RatingDTO rating = this.ratingService.getRating(id);
        return rating != null ? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get ratings by username and/or movie id")
    @ApiResponse(responseCode = "200", description = "Ratings found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Bad request: provide movieId or username", content = @Content)
    @ApiResponse(responseCode = "204", description = "No ratings found", content = @Content)
    public ResponseEntity<List<RatingDTO>> getRatings(
            @RequestParam(required = false) @Valid @Positive Long movieId,
            @RequestParam(required = false) @Valid String username
    ) {
        boolean hasMovie = movieId != null;

        List<RatingDTO> ratings = hasMovie
                ? ratingService.getRatingsByMovie(movieId)
                : ratingService.getRatingsByUser(username);

        return ratings.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(ratings);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new rating")
    @ApiResponse(responseCode = "201", description = "Rating created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    public ResponseEntity<RatingDTO> createRating(
            @Valid @RequestBody RatingCreateDTO ratingCreateDTO
    ) {
        RatingDTO createdRating = this.ratingService.createRating(ratingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a rating by its id")
    @ApiResponse(responseCode = "200", description = "Rating deleted successfully")
    @ApiResponse(responseCode = "404", description = "Rating not found")
    public ResponseEntity<Void> deleteRating(@PathVariable @Positive long id) {
        boolean deleted = ratingService.deleteRating(id);

        if (deleted) {
            return ResponseEntity.ok().build();  // Return 200 OK if deletion was successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if rating was not found
        }
    }

}


