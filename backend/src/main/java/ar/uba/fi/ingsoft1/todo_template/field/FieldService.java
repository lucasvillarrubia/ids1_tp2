

@Service
@Transactional
public class FieldService {
    private final FieldRepository fieldRepository;
    private final FieldScheduleRepository fieldScheduleRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository, FieldScheduleRepository fieldScheduleRepository,
                        ReviewRepository reviewRepository, UserRepository userRepository) {
        this.fieldRepository = fieldRepository;
        this.fieldScheduleRepository = fieldScheduleRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public FieldDTO createField(FieldCreateDTO fieldCreate) {
        return new FielfDTO(fieldRepository.save(fieldCreate.asField()));
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

    public FieldDTO getFieldByName(String name) {
        return new FieldDTO(fieldRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Field not found")));
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

    public ReviewDTO addReviewToField(Long fieldId, ReviewCreateDTO reviewCreate) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        User user = userRepository.findById(reviewCreate.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Review review = new Review(reviewCreate.getRating(), reviewCreate.getComment(), field, user);
        field.addReview(review);
        fieldRepository.save(field);
        return new ReviewDTO(reviewRepository.save(review));
    }

    public void deleteReviewFromField(Long fieldId, Long reviewId) {
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new EntityNotFoundException("Field not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("Review not found"));
        field.removeReview(review);
        fieldRepository.save(field);
        reviewRepository.delete(review);
    }

}