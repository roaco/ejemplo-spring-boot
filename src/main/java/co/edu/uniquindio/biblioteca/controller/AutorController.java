package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.AutorDto;
import co.edu.uniquindio.biblioteca.dto.ClienteDtoGet;
import co.edu.uniquindio.biblioteca.dto.ClienteDtoPost;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.servicios.AutorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
@AllArgsConstructor
public class AutorController {

    private final AutorServicio autorServicio;

    @PostMapping
    public Autor save(@RequestBody Autor autor) {
        return autorServicio.saveAutor(autor);
    }

    @GetMapping("{idAutor}")
    public AutorDto findById(@PathVariable long idAutor) {
        return autorServicio.findById(idAutor);
    }

    @GetMapping
    public List<AutorDto> findAll() {
        return autorServicio.findAll();
    }

    @DeleteMapping("{idAutor}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable long idAutor){
        autorServicio.deleteAutor(idAutor);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Autor eliminado correctamente") );
    }

    @PutMapping("{idAutor}")
    public ResponseEntity<Respuesta<AutorDto>> update(@PathVariable long idAutor, @RequestBody AutorDto autorDto){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("El cliente se modificó correctamente", autorServicio.updateAutor(idAutor, autorDto)));
    }

}
