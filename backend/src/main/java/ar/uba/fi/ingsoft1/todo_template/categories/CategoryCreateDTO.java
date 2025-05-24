package ar.uba.fi.ingsoft1.todo_template.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String name
) {

    public Category asCategory() {
        return this.asCategory(null);
    }

    public Category asCategory(Long id) {
        return new Category(id, this.name);
    }

    public String getName() {
        return name;
    }

}

