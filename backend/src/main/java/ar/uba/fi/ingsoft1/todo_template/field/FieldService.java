package ar.uba.fi.ingsoft1.todo_template.field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.uba.fi.ingsoft1.todo_template.reviews.Review;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewCreateDTO;
import ar.uba.fi.ingsoft1.todo_template.reviews.ReviewDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FieldService {
    @Autowired
    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
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
    public FieldDTO getFieldById(Long fieldId) {
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

    public FieldDTO addReviewToField(ReviewCreateDTO reviewDTO) {
        Field field = fieldRepository.findById(reviewDTO.field_id()).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        Review review = reviewDTO.asReview();
        field.addReview(review.getId());
        return new FieldDTO(fieldRepository.save(field));
    }

}