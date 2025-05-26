package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;
import java.util.Optional;

import ar.uba.fi.ingsoft1.todo_template.FieldSchedule.FieldSchedule;
import ar.uba.fi.ingsoft1.todo_template.reviews.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String zone;

    @Column
    private Double price;

    @Column
    private ArrayList<FieldFeatures> features;

    @OneToMany
    @JoinColumn(name = "field_id", nullable = false, unique = true)
    private ArrayList<String> images;

    @Column
    private Boolean isAvailable = true;

    @OneToOne
    @JoinColumn(name = "schedule_id", nullable = false, unique = true)
    private FieldSchedule schedule;


    private ArrayList<Long> reservations;

    @OneToMany(mappedBy = "field")
    private ArrayList<Review> reviews;

    public Field() {}

    public Field(Long id, String name, Long ownerId, String location, String zone, ArrayList<FieldFeatures> features, Optional<ArrayList<String>> images) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.location = location;
        this.zone = zone;
        this.features = features;
        this.images = images.orElse(new ArrayList<>());

        this.schedule = new FieldSchedule();
        this.reservations = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Field(Long id, Long ownerId, String name, String location, String zone, ArrayList<FieldFeatures> features) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.location = location;
        this.zone = zone;
        this.features = features;

        this.schedule = new FieldSchedule();
        this.images = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Field(Long ownerId, String name, String location, String zone, ArrayList<FieldFeatures> features) {
        this.name = name;
        this.ownerId = ownerId;
        this.location = location;
        this.zone = zone;
        this.features = features;

        this.schedule = new FieldSchedule();
        this.images = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getZone() {
        return zone;
    }

    public Double getPrice() {
        return price;
    }

    public ArrayList<FieldFeatures> getFeatures() {
        return features;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public FieldSchedule getSchedule() {
        return schedule;
    }

    public ArrayList<Long> getReservations() {
        return reservations;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public FieldSchedule getFieldSchedule() {
        return schedule;
    }

    public Boolean IsAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setFeatures(ArrayList<FieldFeatures> features) {
        this.features = features;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setSchedule(FieldSchedule schedule) {
        this.schedule = schedule;
    }

    public void setStartHour(int startHour) {
        this.schedule.setStartHour(startHour);
    }

    public void setEndHour(int endHour) {
        this.schedule.setEndHour(endHour);
    }

    public void setDays(ArrayList<String> days) {
        this.schedule.setDays(days);
    }

    public void setPredefDuration(int predefDuration) {
        this.schedule.setPredefDuration(predefDuration);
    }

    public void setReservations(ArrayList<Long> reservations) {
        this.reservations = reservations;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addReservation(Long reservation) { // TODO: Que se modifique el schedule
        this.reservations.add(reservation);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
