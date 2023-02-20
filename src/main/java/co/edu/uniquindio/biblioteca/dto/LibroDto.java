package co.edu.uniquindio.biblioteca.dto;

import java.time.LocalDate;

public record LibroDto(String isbd, String nombre, String genero, int unidades, LocalDate fechaPublicacion) {
}
