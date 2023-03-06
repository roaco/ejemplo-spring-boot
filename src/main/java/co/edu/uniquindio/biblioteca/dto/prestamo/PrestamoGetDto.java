package co.edu.uniquindio.biblioteca.dto.prestamo;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoGetDto (long prestamoID, long clienteID, List<String> isbnsLibros, LocalDateTime fechaCreacion,
                              LocalDateTime fechaDevolucion){
}
