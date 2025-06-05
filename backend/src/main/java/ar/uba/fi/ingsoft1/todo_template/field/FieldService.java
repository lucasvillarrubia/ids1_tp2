package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.ingsoft1.todo_template.reviews.Review;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@Transactional
public class FieldService {
    @Autowired
    private final FieldRepository fieldRepository;
    private final ReviewRepository reviewRepository;

    public FieldService(FieldRepository fieldRepository, 
                        ReviewRepository reviewRepository) {
        this.fieldRepository = fieldRepository;
        this.reviewRepository = reviewRepository;
    }

    public FieldDTO createField(FieldCreateDTO fieldCreate) {
        Field newField = fieldCreate.asField();
        if (!fieldRepository.findByName(newField.getName()).isEmpty()) {
            throw new IllegalArgumentException("Field with this name already exists.");
        }

        Field savedField = fieldRepository.save(newField);
        return new FieldDTO(savedField);
    }

    public void deleteField(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        fieldRepository.delete(field);
    }

    // GET
    public FieldDTO getFieldById(Long fieldId) throws MethodArgumentNotValidException {
        return new FieldDTO(fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found")));
    }

    public List<FieldDTO> getAllFields() {
        return fieldRepository.findAll().stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldsByOwnerId(Long ownerId) {
        return fieldRepository.findByOwnerId(ownerId).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldsByZone(String zone) {
        return fieldRepository.findByZone(zone).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldByName(String name) {
        return fieldRepository.findByName(name).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<FieldDTO> getFieldsByFeature(FieldFeatures feature) {
        return fieldRepository.findByFeaturesContaining(feature).stream().map(FieldDTO::new).collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByFieldId(Long fieldId) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        return field.getReviews().stream()
                .map(reviewRepository::findById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }

    // UPDATE
    public FieldDTO updateFieldDescription(Long fieldId, String description) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        field.setDescription(description);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldFeatures(Long fieldId, ArrayList<FieldFeatures> features) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        field.setFeatures(features);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldPrice(Long fieldId, Double price) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        field.setPrice(price);
        return new FieldDTO(fieldRepository.save(field));
    }

    public FieldDTO updateFieldImages(Long fieldId, ArrayList<String> images) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        field.setImages(images);
        return new FieldDTO(fieldRepository.save(field));
    }

    public ReviewDTO addReviewToField(ReviewCreateDTO reviewDTO) {
        Field field = fieldRepository.findById(reviewDTO.field_id()).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        Review review = reviewRepository.save(reviewDTO.asReview());
        field.addReview(review.getId());
        fieldRepository.save(field);
        return new ReviewDTO(review);
    }

    // edit field
    public FieldDTO updateField(Long fieldId, FieldUpdateDTO fieldEdit) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));

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

        return new FieldDTO(fieldRepository.save(field));
    }

}