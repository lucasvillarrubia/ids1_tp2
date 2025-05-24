package ar.uba.fi.ingsoft1.todo_template.categories;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@Tag(name = "5 - Categories")
public class CategoryRestController {

    private final CategoryService catService;

    public CategoryRestController(CategoryService catService) {
        this.catService = catService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "201", description = "Category created", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CategoryCreateDTO catCreateDTO
    ) {
        CategoryDTO createdCat = this.catService.createCategory(catCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCat);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a category by its id")
    @ApiResponse(responseCode = "200", description = "Category updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable @Positive long id,
            @Valid @RequestBody CategoryCreateDTO catCreateDTO
    ) {
        CategoryDTO updatedCat = catService.updateCategory(id, catCreateDTO);

        if (updatedCat != null) {
            return ResponseEntity.ok(updatedCat);  // Return updated category with 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if category was not found
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a category by its id")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Optional: restrict to admin
    public ResponseEntity<Void> deleteCategory(@PathVariable @Positive long id) {
        boolean deleted = catService.deleteCategory(id);

        if (deleted) {
            return ResponseEntity.ok().build();  // Return 200 OK if deletion was successful
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if category was not found
        }
    }

}


