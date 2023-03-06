package co.edu.uniquindio.biblioteca.dto.libro;

import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.entity.Genero;

import java.time.LocalDate;
import java.util.List;

public record LibroDto(String isbn, String nombre, Genero genero, int unidades, LocalDate fechaPublicacion, List<Long> idAutores){
}
