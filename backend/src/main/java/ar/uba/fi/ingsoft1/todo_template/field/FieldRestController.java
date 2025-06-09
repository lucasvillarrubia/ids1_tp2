package ar.uba.fi.ingsoft1.todo_template.field;

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
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reservation.ReservationDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/fields")
@Tag(name = "Fields")
public class FieldRestController {

    private final FieldService fieldService;

    public FieldRestController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

//    @GetMapping(value = "/all", produces = "application/json")
    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all fields")
//    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "200", description = "Fields found")
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFields() {
        return fieldService.getAllFields().stream().toList();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a football field given the specified id")
    @ApiResponse(responseCode = "200", description = "Field found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    public FieldDTO getFieldById(@PathVariable @Positive Long id) throws MethodArgumentNotValidException {
        return fieldService.getFieldById(id);
    }

    @GetMapping(value = "/owner/{ownerEmail}", produces = "application/json")
    @Operation(summary = "Get all fields by owner email")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFieldsByOwnerId(@PathVariable @Positive String ownerEmail) {
        return fieldService.getFieldsByOwner(ownerEmail).stream().toList();
    }

    @GetMapping(value = "/owner/me", produces = "application/json")
    @Operation(summary = "Get all fields owned by the current user")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFieldsOwns() {
        String ownerEmail = fieldService.getCurrentUser().getEmail();
        return fieldService.getFieldsByOwner(ownerEmail).stream().toList();
    }

    @GetMapping(value = "/zone/{zone}", produces = "application/json")
    @Operation(summary = "Get all fields by zone")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFieldsByZone(@PathVariable UserZones zone) {
        return fieldService.getFieldsByZone(zone).stream().toList();
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    @Operation(summary = "Get all fields by name. This is a case-sensitive search and match the full name.")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFieldByName(@PathVariable String name) {
        return fieldService.getFieldByName(name).stream().toList();
    }

    @GetMapping(value = "/feature/{feature}", produces = "application/json")
    @Operation(summary = "Get all fields by feature")
    @ApiResponse(responseCode = "200", description = "Fields found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Fields not found", content = @Content)
    public List<FieldDTO> getFieldsByFeature(@PathVariable FieldFeatures feature) {
        return fieldService.getFieldsByFeature(feature).stream().toList();
    }

    @GetMapping(value = "{id}/reviews/", produces = "application/json")
    @Operation(summary = "Get all reviews for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reviews found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public List<ReviewDTO> getReviewsByFieldId(@PathVariable @Positive Long id) {
        return fieldService.getReviewsByFieldId(id).stream().toList();
    }

    @GetMapping(value = "{id}/reservations/", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public List<ReservationDTO> getReservationsByFieldId(@PathVariable @Positive Long id) {
        return fieldService.getReservationsByFieldId(id).stream().toList();
    }

    @GetMapping(value = "{id}/reservations/availableSlots", produces = "application/json")
    @Operation(summary = "Get all available time slots for a field by its id")
    @ApiResponse(responseCode = "200", description = "Available time slots found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public List<String> getAvailableTimeSlotsByFieldId(@PathVariable @Positive Long id, 
                                                       @RequestParam(required = true) String date) {
        return fieldService.getAvailableSlotsForReservations(date, id).stream().map(Object::toString).toList();
    }

    @GetMapping(value = "/reservations/organizer/{organizerEmail}", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public List<ReservationDTO> getReservationsByOrganizer(@PathVariable @Positive String organizerEmail) {
        return fieldService.getReservationByOrganizerEmail(organizerEmail).stream().toList();
    }

    @GetMapping(value = "/reservations/organizer/me", produces = "application/json")
    @Operation(summary = "Get all reservations for a field by its id")
    @ApiResponse(responseCode = "200", description = "Reservations found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public List<ReservationDTO> getMyReservations() {
        String organizerEmail = fieldService.getCurrentUser().getEmail();
        return fieldService.getReservationByOrganizerEmail(organizerEmail).stream().toList();
    }

    @PostMapping(value = "/{id}/reservations/unavailableSlots", produces = "application/json")
    @Operation(summary = "Block time slots for a field by its id")
    @ApiResponse(responseCode = "200", description = "Unavailable time slots blocked", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Void> blockTimeSlots(
            @PathVariable @Positive Long id,
            @Valid @RequestBody TimeSlotDTO unavailableTimeSlots
    ) {
        fieldService.addUnavailbleTimeSlotToField(id, unavailableTimeSlots);
        return ResponseEntity.ok().build();
    }
    /* ejemplo del body para crear una cancha
{
    owner_id: 1,
    name: "Cancha 1",
    description: "Cancha de futbol 5",
    location: "Calle Falsa 123",
    zone: "San Telmo",
    features: ["GRASS", "LIGHTS", "RESTROOMS"]
}
    */

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new Field")
    @ApiResponse(responseCode = "201", description = "Field created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public FieldDTO createField(
            @Valid @RequestBody FieldCreateDTO fieldCreateDTO
    ) {
        System.out.println("CONTROLLER Creating Field with DTO: " + fieldCreateDTO);
        FieldDTO createdField = this.fieldService.createField(fieldCreateDTO);
        return createdField;
    }

    @PostMapping(consumes = "application/json", produces = "application/json", value = "/{id}/reviews")
    @Operation(summary = "Create a new review for a field")
    @ApiResponse(responseCode = "201", description = "Review created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')") 
    public ReviewDTO createReview(
            @Valid @RequestBody ReviewCreateDTO reviewCreateDTO
    ) {
        return fieldService.addReviewToField(reviewCreateDTO);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", value = "/{id}/reservations")
    @Operation(summary = "Create a new reservation for a field")
    @ApiResponse(responseCode = "201", description = "Reservation created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ReservationDTO createReservation(
            @Valid @RequestBody ReservationCreateDTO reservationCreateDTO
    ) {
        return fieldService.addReservationToField(reservationCreateDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a field by its id")
    @ApiResponse(responseCode = "200", description = "Field deleted successfully")
    @ApiResponse(responseCode = "404", description = "Field not found")
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Void> deleteField(@PathVariable @Positive long id) {
        fieldService.deleteField(id);
        return ResponseEntity.ok().build(); // TODO: chequear si se eliminó
    }

    // PATCH

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a field by its id")
    @ApiResponse(responseCode = "200", description = "Field updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Field not found", content = @Content)
    public FieldDTO updateField(
            @PathVariable @Positive Long id,
            @Valid @RequestBody FieldUpdateDTO fieldUpdateDTO
    ) {
        return fieldService.updateField(id, fieldUpdateDTO);
    }

}