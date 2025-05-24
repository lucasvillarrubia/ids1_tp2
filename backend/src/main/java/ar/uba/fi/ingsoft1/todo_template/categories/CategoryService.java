package ar.uba.fi.ingsoft1.todo_template.categories;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
class CategoryService {

    final private Map<Long, CategoryDTO> categories;

    public CategoryService() {
        this.categories = new HashMap<>();

        Category sciFi = new Category(1L, "Science Fiction");
        Category drama = new Category(2L, "Drama");
        Category action = new Category(3L, "Action");
        Category adventure = new Category(4L, "Adventure");

        this.categories.put(sciFi.getId(), new CategoryDTO(sciFi));
        this.categories.put(drama.getId(), new CategoryDTO(drama));
        this.categories.put(action.getId(), new CategoryDTO(action));
        this.categories.put(adventure.getId(), new CategoryDTO(adventure));
    }

    CategoryDTO getCategory(long id) {
        return this.categories.get(id);
    }

    public CategoryDTO updateCategory(long id, CategoryCreateDTO catCreateDTO) {
        if (this.categories.containsKey(id)) {
            this.categories.put(id, new CategoryDTO(catCreateDTO.asCategory(id)));
        }
        return null;
    }

    boolean deleteCategory(long id) {
        if (this.categories.containsKey(id)) {
            this.categories.remove(id);
            return true;
        }
        return false;
    }

    public CategoryDTO createCategory(CategoryCreateDTO dto) {
        String name = "Terror";
        return new CategoryDTO(5L, name);
    }

}

