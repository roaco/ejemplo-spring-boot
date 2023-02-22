package co.edu.uniquindio.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Es la estructura de la respuesta
 * @param <T>
 */
@AllArgsConstructor
@Data
public class Respuesta <T>{

    private String mensaje;
    private T dato;

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
    }
}

