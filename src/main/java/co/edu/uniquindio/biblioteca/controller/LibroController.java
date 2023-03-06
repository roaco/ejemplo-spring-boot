package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.libro.LibroDto;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.servicios.LibroServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@AllArgsConstructor
public class LibroController {

    private final LibroServicio libroServicio;

    /**
     * Post - Save
     * @param libroDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Respuesta<Libro>> save(@RequestBody LibroDto libroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Libro creado correctamente", libroServicio.save(libroDTO)));
    }

    /**
     *Get all
     * @return
     */
    @GetMapping
    public ResponseEntity<Respuesta<List<Libro>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", libroServicio.findAll()));
    }

    /**
     *Get by ID
     * @param isbnLibro
     * @return
     */
    @GetMapping("/{isbnLibro}")
    public ResponseEntity<Respuesta<Libro>> findById(@PathVariable String isbnLibro){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", libroServicio.findById(isbnLibro)) );
    }

    /**
     *Delete
     * @param isbn
     * @return
     */
    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        libroServicio.deleteLibro(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El libro se eliminó correctamente"));
    }

    /**
     *Update
     * @param isbn
     * @param libro
     * @return
     */
    @PutMapping("/{isbn}")
    public ResponseEntity<Respuesta<Libro>> update(@PathVariable String isbn, @RequestBody LibroDto libro){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("El libro se actualizo correctamente", libroServicio.updateLibro(isbn, libro)) );
    }
}
