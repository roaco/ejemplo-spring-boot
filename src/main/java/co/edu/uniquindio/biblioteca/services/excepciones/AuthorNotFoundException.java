package co.edu.uniquindio.biblioteca.services.excepciones;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
