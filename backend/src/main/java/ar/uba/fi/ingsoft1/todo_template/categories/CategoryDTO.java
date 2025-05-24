package ar.uba.fi.ingsoft1.todo_template.categories;

public record CategoryDTO(
        long id,
        String name
) {

    public CategoryDTO(Category cat) {
        this(cat.getId(), cat.getName());
    }

}

