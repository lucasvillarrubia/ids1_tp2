package ar.uba.fi.ingsoft1.todo_template.user;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
