package co.edu.uniquindio.biblioteca.servicios.excepciones;

public class LibroNoEncontradoException extends RuntimeException{
    public LibroNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
