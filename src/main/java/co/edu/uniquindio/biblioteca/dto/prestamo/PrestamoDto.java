package co.edu.uniquindio.biblioteca.dto.prestamo;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoDto(long clienteId, List<String> isbnLibros, LocalDateTime fechaDevolucion) {
}
