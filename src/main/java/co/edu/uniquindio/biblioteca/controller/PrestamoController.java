package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.dto.prestamo.PrestamoDto;
import co.edu.uniquindio.biblioteca.dto.prestamo.PrestamoGetDto;
import co.edu.uniquindio.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/prestamo")
@AllArgsConstructor
public class PrestamoController {
    public final PrestamoServicio prestamoServicio;

    /**
     * Delete
     * @param prestamoId
     * @return
     */
    @DeleteMapping("/{prestamoId}")
    public ResponseEntity<Respuesta<String>> deleteLoan(@PathVariable Long prestamoId) {
        prestamoServicio.delete(prestamoId);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El Prestamo se eliminó correctamente"));
    }

    /**
     * Find all
     * @return
     */
    @GetMapping
    public ResponseEntity<Respuesta<List<PrestamoGetDto>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Prestamos encontrados", prestamoServicio.findAll()));
    }

    /**
     * Find by ID
     * @param prestamoId
     * @return
     */
    @GetMapping("/{prestamoId}")
    public ResponseEntity<Respuesta<PrestamoGetDto>> findById(@PathVariable long prestamoId) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Prestamo encontrado", prestamoServicio.findById(prestamoId)));
    }

    /**
     * Post - Save
     * @param prestamo
     * @return
     */
    @PostMapping
    public ResponseEntity<Respuesta<PrestamoDto>> save(@RequestBody PrestamoDto prestamo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Prestamo creado exitosamente", prestamoServicio.save(prestamo)));
    }

    /**
     * Update
     * @param prestamoId
     * @param prestamo
     * @return
     */
    @PutMapping("/{prestamoId}")
    public ResponseEntity<Respuesta<PrestamoDto>> update(@PathVariable long prestamoId, @RequestBody PrestamoDto prestamo) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Prestamos actualizado exitosamente", prestamoServicio.update(prestamoId, prestamo)));
    }
}
