package ar.uba.fi.ingsoft1.todo_template.field;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import ar.uba.fi.ingsoft1.todo_template.common.exception.DuplicateEntityException;
import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import ar.uba.fi.ingsoft1.todo_template.reservation.*;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import ar.uba.fi.ingsoft1.todo_template.user.UserZones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldSchedule;
import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldScheduleCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.TimeSlot;
import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.TimeSlotDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.Review;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FieldService {
    @Autowired
    private final FieldRepository fieldRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;
    private final UserService userService;

    public FieldService(FieldRepository fieldRepository,
            ReviewRepository reviewRepository,
            ReservationService reservationService,
            UserService userService) {
        this.fieldRepository = fieldRepository;
        this.reviewRepository = reviewRepository;
        this.reservationService = reservationService;
        this.userService = userService;
    }

    public FieldDTO createField(FieldCreateDTO fieldCreate) {
        Field newField = fieldCreate.asField(userService.getCurrentUser());
        if (!fieldRepository.findByName(newField.getName()).isEmpty()) {
            throw new DuplicateEntityException("Field", "name");
        }

        Field savedField = fieldRepository.save(newField);
        return new FieldDTO(savedField);
    }

    public void deleteField(Long fieldId) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(),
                "Solo el propietario del campo puede eliminarlo");

        if (!field.getReservations().isEmpty()) {
            throw new InvalidActionException("No se puede eliminar un campo con reservas asociadas");
        }

        fieldRepository.delete(field);
    }

    public FieldDTO getFieldById(Long fieldId) {
        return new FieldDTO((findFieldById(fieldId)));
    }
    public List<FieldDTO> getAllFields() {
        return fieldRepository.findAll().stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getCurrentUserOwnedFields() {
        return getFieldsByOwner(userService.getCurrentUserEmail());
    }

    public List<FieldDTO> getFieldsByOwner(String ownerEmail) {
        User user = userService.getUserByEmail(ownerEmail);
        return fieldRepository.findByOwnerId(user.getId()).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldsByZone(UserZones zone) {
        return fieldRepository.findByZone(zone).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldByName(String name) {
        return fieldRepository.findByName(name).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldsByFeature(FieldFeatures feature) {
        return fieldRepository.findByFeaturesContaining(feature).stream().map(FieldDTO::new)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByFieldId(Long fieldId) {
        Field field = findFieldById(fieldId);
        return field.getReviews().stream()
                .map(reviewRepository::findById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getStaticticsByFieldId(Long fieldId) {
        Field field = findFieldById(fieldId);
        List<ReservationDTO> reservations = reservationService.getReservationsByFieldId(fieldId);

        double weekly = field.getWeeklyOcupation(reservations);
        double monthly = field.getMonthlyOcupation(reservations);
        int totalReservations = reservations.stream().filter(r -> r.getDate().isAfter(LocalDate.now()))
                .toList().size();
        totalReservations += reservations.stream().filter(r -> r.getDate().isEqual(LocalDate.now()))
                .toList().size();

        Map<String, Object> response = new HashMap<>();
        response.put("weeklyOccupation", weekly);
        response.put("monthlyOccupation", monthly);
        response.put("totalReservations", totalReservations);

        return response;
    }

    public void deleteReservation(Long reservationId) {
        Field field = reservationService.deleteReservation(reservationId);
        fieldRepository.save(field);
    }

    public FieldDTO updateFieldDescription(Long fieldId, String description) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(), "Solo el propietario del campo puede editarlo");
        field.setDescription(description);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldFeatures(Long fieldId, ArrayList<FieldFeatures> features) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(), "Solo el propietario del campo puede editarlo");
        field.setFeatures(features);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldPrice(Long fieldId, Double price) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(), "Solo el propietario del campo puede editarlo");
        field.setPrice(price);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldImages(Long fieldId, ArrayList<String> images) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(), "Solo el propietario del campo puede editarlo");
        field.setImages(images);
        return new FieldDTO(fieldRepository.save(field));
    }

    public ReviewDTO addReviewToField(ReviewCreateDTO reviewDTO) {
        Field field = findFieldById(reviewDTO.field_id());
        Review review = reviewRepository.save(reviewDTO.asReview());
        field.addReview(review.getId());
        fieldRepository.save(field);
        return new ReviewDTO(review);
    }

    public ReservationDTO addReservationToField(ReservationCreateDTO reservation) {
        Field field = findFieldById(reservation.getFieldId());
        Reservation newReservation = reservationService.makeReservation(reservation, field);
        field.addReservation(newReservation);
        return new ReservationDTO(newReservation);
    }

    public List<String> getAvailableSlotsForReservations(LocalDate date, Long fieldId) {
        Field field = findFieldById(fieldId);
        return reservationService.getAvailableSlotsForReservations(date, fieldId, field.getFieldSchedule());
    }

    public FieldDTO addUnavailbleTimeSlotToField(Long fieldId, TimeSlotDTO timeSlot) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(),
                "Solo el propietario del campo puede editarlo");

        TimeSlot timeSlotEntity = timeSlot.asTimeSlot();
        FieldSchedule fieldSchedule = field.getSchedule();
        reservationService.addUnavailableTimeSlotToField(fieldId, timeSlotEntity, fieldSchedule);

        fieldSchedule.addUnavailableTimeSlot(timeSlotEntity);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateField(Long fieldId, FieldUpdateDTO fieldEdit) {
        Field field = findFieldById(fieldId);
        checkIfCurrentUserIsOwner(field.getOwner().getEmail(),
                "Solo el propietario del campo puede editarlo");

        if (fieldEdit.getDescription() != null) {
            field.setDescription(fieldEdit.getDescription());
        }

        if (fieldEdit.getLocation() != null) {
            field.setLocation(fieldEdit.getLocation());
        }

        if (fieldEdit.getZone() != null) {
            field.setZone(fieldEdit.getZone());
        }

        if (fieldEdit.getPrice() != null) {
            field.setPrice(fieldEdit.getPrice().doubleValue());
        }

        if (fieldEdit.getFeatures() != null) {
            field.setFeatures(fieldEdit.getFeatures().stream()
                    .map(FieldFeatures::valueOf)
                    .collect(Collectors.toList()));
        }

        if (fieldEdit.getImages() != null) {
            field.setImages(fieldEdit.getImages());
        }

        if (fieldEdit.getName() != null) {
            field.setName(fieldEdit.getName());
        }

        if (fieldEdit.schedule() != null) {
            FieldSchedule schedule = field.getSchedule();
            FieldScheduleCreateDTO scheduleDTO = fieldEdit.schedule();

            if (scheduleDTO.getDays() != null) {
                schedule.setDays(scheduleDTO.getDays());
            }

            if (scheduleDTO.getStartHour() != null) {
                schedule.setStartHour(scheduleDTO.getStartHour());
            }

            if (scheduleDTO.getEndHour() != null) {
                schedule.setEndHour(scheduleDTO.getEndHour());
            }

            if (schedule.getStartHour().isAfter(schedule.getEndHour())) {
                throw new IllegalArgumentException("Start hour must be before end hour");
            }

            if (scheduleDTO.getPredefDuration() != null) {
                schedule.setPredefDuration(scheduleDTO.getPredefDuration());
            }
        }

        return new FieldDTO(fieldRepository.save(field));
    }

    private void checkIfCurrentUserIsOwner(String email, String msg){
        String currentUserEmail = userService.getCurrentUserEmail();
        if(!currentUserEmail.equals(email)){
            throw new InvalidActionException(msg);
        }
    }

    private Field findFieldById(Long id){
        Optional<Field> field = fieldRepository.findById(id);
        if (field.isEmpty()) {
            throw new EntityNotFoundException("No se encontró la cancha");
        }
        return field.get();
    }

}