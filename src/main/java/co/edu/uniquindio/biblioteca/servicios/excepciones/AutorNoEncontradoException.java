package co.edu.uniquindio.biblioteca.servicios.excepciones;

public class AutorNoEncontradoException extends RuntimeException{
    public AutorNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
