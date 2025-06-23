package ar.uba.fi.ingsoft1.todo_template.reservation;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldSchedule;
import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.TimeSlot;
import ar.uba.fi.ingsoft1.todo_template.common.exception.DuplicateEntityException;
import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import ar.uba.fi.ingsoft1.todo_template.field.Field;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository, UserService userService){
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    public List<ReservationDTO> getReservationsByFieldId(Long fieldId) {
        return reservationRepository.findByFieldId(fieldId).stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    public List<ReservationDTO> getCurrentUserReservations(){
        return getReservationByOrganizerEmail(userService.getCurrentUserEmail());
    }

    public List<ReservationDTO> getReservationByOrganizerEmail(String organizerEmail) {
        return reservationRepository.findByOrganizerEmail(organizerEmail).stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    public Field deleteReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        Field field = reservation.getField();

        checkIfCurrentUserIsOrganizer(reservation,
                "Solo el organizador de la reserva puede eliminarla");
        field.removeReservation(reservationId);
        reservationRepository.deleteById(reservationId);

        return field;
    }

    public Reservation makeReservation(ReservationCreateDTO reservation, Field field) {
        FieldSchedule fieldSchedule = field.getFieldSchedule();
        fieldSchedule.checkForValidHours(reservation);
        checkForCompatibleTimeSlots(reservation, field.getId());
        fieldSchedule.checkForAvailableTimeSlot(reservation);

        User organizer = userService.getCurrentUser();
        return reservationRepository.save(reservation.asReservation(field, organizer));
    }

    public Reservation getReservationById(Long reservationId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if  (reservation.isEmpty()) {
            throw new EntityNotFoundException("Reservation not found");
        }
        return reservation.get();
    }

    public List<String> getAvailableSlotsForReservations(LocalDate date, Long fieldId, FieldSchedule fieldSchedule) {
        List<ReservationDTO> reservations = reservationRepository.findByFieldIdAndDate(fieldId, date)
                .stream()
                .map(ReservationDTO::new)
                .toList();
        List<TimeSlot> timeSlots = fieldSchedule.getTimeSlotsForDate(date, reservations);

        return timeSlots.stream()
                .map(slot -> slot.getStartHour() + " - " + slot.getEndHour())
                .collect(Collectors.toList());
    }

    public void addUnavailableTimeSlotToField(Long fieldId, TimeSlot timeSlot, FieldSchedule fieldSchedule) {
        List<Reservation> reservations = reservationRepository.findByFieldIdAndDate(fieldId, timeSlot.getDate());
        if (reservations.stream()
                .anyMatch(reservation -> timeSlot.getStartHour().isBefore(reservation.getEnd()) &&
                        timeSlot.getEndHour().isAfter(reservation.getStart()))) {
            throw new DuplicateEntityException("Reservation", "time slot");
        }

        if (fieldSchedule.getUnavailableTimeSlots().stream()
                .anyMatch(unavailable -> timeSlot.getStartHour().isBefore(unavailable.getEndHour()) &&
                        timeSlot.getEndHour().isAfter(unavailable.getStartHour()))) {
            throw new DuplicateEntityException("Blocked Timeslot", "time");
        }
    }

    private void checkIfCurrentUserIsOrganizer(Reservation reservation, String msg){
        String currentUserEmail = userService.getCurrentUserEmail();
        if (reservation.getOrganizer().getEmail().equals(currentUserEmail)) {
            throw new InvalidActionException(msg);
        }
    }

    private void checkForCompatibleTimeSlots(ReservationCreateDTO reservation, Long id) {
        List<Reservation> reservations = reservationRepository.findByFieldIdAndDate(id,
                reservation.getDate());
        if (reservations.stream().anyMatch(r -> r.getStart().isBefore(reservation.getEnd()) &&
                r.getEnd().isAfter(reservation.getStart()))) {
            throw new DuplicateEntityException("Reservation", "time slot");
        }
    }

    private void checkForAvailableTimeSlot() {}
}
