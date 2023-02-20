package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.AutorDto;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.servicios.AutorServicio;
import lombok.AllArgsConstructor;
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
    public String delete(@PathVariable long idAutor) {
        autorServicio.deleteAutor(idAutor);
        return "Se elimino autor";
    }

    @PutMapping("{idAutor}")
    public Autor update(@PathVariable long idAutor, @RequestBody Autor autorNuevo) {
        return autorServicio.updateAutor(idAutor, autorNuevo);
    }

}
