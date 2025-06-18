package ar.uba.fi.ingsoft1.todo_template.field;

import java.time.LocalDate;
import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.user.UserZones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.TimeSlotDTO;
import ar.uba.fi.ingsoft1.todo_template.common.exception.DuplicateEntityException;
import ar.uba.fi.ingsoft1.todo_template.common.exception.GlobalExceptionHandler;
import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/fields")
@Tag(name = "Fields")
public class FieldRestController {

    private final FieldService fieldService;
    private final GlobalExceptionHandler globalExceptionHandler;

    public FieldRestController(FieldService fieldService) {
        this.fieldService = fieldService;
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all fields")
    @ApiResponse(responseCode = "200", description = "Fields found")
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFields() {
        return fieldService.getAllFields();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a football field given the specified id")
    @ApiResponse(responseCode = "200", description = "Field found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> getFieldById(@PathVariable @Positive Long id) {
        try {
            return ResponseEntity.ok(fieldService.getFieldById(id));
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "/owner/{ownerEmail}", produces = "application/json")
    @Operation(summary = "Get all fields by owner email")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
    public ResponseEntity<?> getFieldsByOwnerId(@PathVariable String ownerEmail) {
        try {
            return ResponseEntity.ok(fieldService.getFieldsByOwner(ownerEmail).stream().toList());
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "/owner/me", produces = "application/json")
    @Operation(summary = "Get all fields owned by the current user")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
    public ResponseEntity<?> getFieldsOwns() {
        try {
            String ownerEmail = fieldService.getCurrentUser().getEmail();
            return ResponseEntity.ok(fieldService.getFieldsByOwner(ownerEmail).stream().toList());
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "/zone/{zone}", produces = "application/json")
    @Operation(summary = "Get all fields by zone")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public ResponseEntity<?> getFieldsByZone(@PathVariable UserZones zone) {
        try {
            return ResponseEntity.ok(fieldService.getFieldsByZone(zone).stream().toList());
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    @Operation(summary = "Get all fields by name. This is a case-sensitive search and match the full name.")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    public List<FieldDTO> getFieldByName(@PathVariable String name) {
        return fieldService.getFieldByName(name).stream().toList();
    }

    @GetMapping(value = "/feature/{feature}", produces = "application/json")
    @Operation(summary = "Get all fields by feature")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    public List<FieldDTO> getFieldsByFeature(@PathVariable FieldFeatures feature) {
        return fieldService.getFieldsByFeature(feature).stream().toList();
    }

    @GetMapping(value = "{id}/reviews/", produces = "application/json")
    @Operation(summary = "Get all reviews for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reviews found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public ResponseEntity<?> getReviewsByFieldId(@PathVariable @Positive Long id) {
        try {
            return ResponseEntity.ok(fieldService.getReviewsByFieldId(id).stream().toList());
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "{id}/reservations/", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public ResponseEntity<?> getReservationsByFieldId(@PathVariable @Positive Long id) {
        try {
            return ResponseEntity.ok(fieldService.getReservationsByFieldId(id).stream().toList());
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "{id}/reservations/availableSlots", produces = "application/json")
    @Operation(summary = "Get all available time slots for a field by its id")
    @ApiResponse(responseCode = "200", description = "Available time slots found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public ResponseEntity<?> getAvailableTimeSlotsByFieldId(@PathVariable @Positive Long id,
            @RequestParam(required = true) LocalDate date) {
        try {
            return ResponseEntity.ok(
                    fieldService.getAvailableSlotsForReservations(date, id));
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @GetMapping(value = "/reservations/organizer/{organizerEmail}", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    public List<ReservationDTO> getReservationsByOrganizer(@PathVariable @Positive String organizerEmail) {
        return fieldService.getReservationByOrganizerEmail(organizerEmail).stream().toList();
    }

    @GetMapping(value = "/reservations/organizer/me", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    public List<ReservationDTO> getMyReservations() {
        String organizerEmail = fieldService.getCurrentUser().getEmail();
        return fieldService.getReservationByOrganizerEmail(organizerEmail).stream().toList();
    }

    @GetMapping(value = "/{id}/reservations/statistics", produces = "application/json")
    @Operation(summary = "Get reservation statistics for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservation statistics found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public ResponseEntity<?> getReservationStatistics(@PathVariable @Positive Long id) {
        try {
            return ResponseEntity.ok(fieldService.getStaticticsByFieldId(id));
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @PostMapping(value = "/{id}/reservations/unavailableSlots", produces = "application/json")
    @Operation(summary = "Block time slots for a field by its id")
    @ApiResponse(responseCode = "200", description = "Unavailable time slots blocked", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "403", description = "User is not authorized to block time slots for this field", content = @Content)
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ApiResponse(responseCode = "409", description = "Time slot is unavailable", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> blockTimeSlots(
            @PathVariable @Positive Long id,
            @Valid @RequestBody TimeSlotDTO unavailableTimeSlots) {
        try {
            fieldService.addUnavailbleTimeSlotToField(id, unavailableTimeSlots);
            return ResponseEntity.ok().build();
        } catch (DuplicateEntityException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleDuplicateEntityException(e).getBody();
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (InvalidActionException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleInvalidActionException(e).getBody();
        }
    }

    /*
     * ejemplo del body para crear una cancha
     * {
     * "name": "Cancha 1",
     * "description": "Cancha de futbol 5",
     * "location": "Calle Falsa 123",
     * "zone": EZEIZA,
     * "features": ["GRASS", "LIGHTS", "RESTROOMS"]
     * }
     */

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Field")
    @ApiResponse(responseCode = "201", description = "Field created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User is not logged in to create this field", content = @Content)
    @ApiResponse(responseCode = "409", description = "Field with the same name already exists", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createField(
            @Valid @RequestBody FieldCreateDTO fieldCreateDTO) {
        try {
            FieldDTO createdField = this.fieldService.createField(fieldCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (DuplicateEntityException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleDuplicateEntityException(e).getBody();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json", value = "/{id}/reviews")
    @Operation(summary = "Create a new review for a field")
    @ApiResponse(responseCode = "201", description = "Review created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReview(
            @Valid @RequestBody ReviewCreateDTO reviewCreateDTO) {
        try {
            ReviewDTO createdReview = fieldService.addReviewToField(reviewCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json", value = "/{id}/reservations")
    @Operation(summary = "Create a new reservation for a field")
    @ApiResponse(responseCode = "201", description = "Reservation created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ApiResponse(responseCode = "409", description = "Unavailable time slot", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createReservation(
            @Valid @RequestBody ReservationCreateDTO reservationCreateDTO) {
        try {
            ReservationDTO createdReservation = fieldService.addReservationToField(reservationCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (DuplicateEntityException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleDuplicateEntityException(e).getBody();
        } catch (IllegalArgumentException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleIllegalArgumentException(e).getBody();
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a field by its id")
    @ApiResponse(responseCode = "200", description = "Field deleted successfully")
    @ApiResponse(responseCode = "404", description = "Field not found")
    @ApiResponse(responseCode = "403", description = "User is not authorized to delete this field")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteField(@PathVariable @Positive long id) {
        try {
            fieldService.deleteField(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (InvalidActionException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleInvalidActionException(e).getBody();
        }
    }

    @DeleteMapping(value = "/reservations/{reservationId}")
    @Operation(summary = "Delete a reservation by its id")
    @ApiResponse(responseCode = "200", description = "Reservation deleted successfully")
    @ApiResponse(responseCode = "403", description = "User is not authorized to delete this reservation")
    @ApiResponse(responseCode = "404", description = "Reservation not found")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteReservation(
            @PathVariable @Positive Long reservationId) {
        try {
            fieldService.deleteReservationByOwner(reservationId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (InvalidActionException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleInvalidActionException(e).getBody();
        }
    }

    // PATCH

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a field by its id")
    @ApiResponse(responseCode = "200", description = "Field updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "403", description = "User is not authorized to update this field", content = @Content)
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public ResponseEntity<?> updateField(
            @PathVariable @Positive Long id,
            @Valid @RequestBody FieldUpdateDTO fieldUpdateDTO) {
        try {
            FieldDTO updatedField = fieldService.updateField(id, fieldUpdateDTO);
            return ResponseEntity.ok(updatedField);
        } catch (EntityNotFoundException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleEntityNotFoundException(e).getBody();
        } catch (InvalidActionException e) {
            return (ResponseEntity<?>) globalExceptionHandler.handleInvalidActionException(e).getBody();
        }
    }

}