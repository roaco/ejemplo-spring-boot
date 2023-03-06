package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.cliente.ClienteDtoGet;
import co.edu.uniquindio.biblioteca.dto.cliente.ClienteDtoPost;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;

    /**
     *
     * @param cliente
     * @return
     */
    @PostMapping
    public ResponseEntity<Respuesta<ClienteDtoGet>> save(@RequestBody ClienteDtoPost cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Cliente creado correctamente", clienteServicio.saveCliente(cliente)) );
    }

    /**
     *
     * @param idCliente
     * @return
     */
    @GetMapping("/{idCliente}")
    public ResponseEntity<Respuesta<ClienteDtoGet>> findById(@PathVariable long idCliente){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", clienteServicio.findById(idCliente)) );
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Respuesta<List<ClienteDtoGet>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", clienteServicio.findAll()) );

    }

    /**
     *
     * @param idCliente
     * @return
     */
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable long idCliente){
        clienteServicio.deleteCliente(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Se eliminó correctamente") );
    }

    /**
     *
     * @param idCliente
     * @param cliente
     * @return
     */
    @PutMapping("/{idCliente}")
    public ResponseEntity<Respuesta<ClienteDtoGet>> update(@PathVariable long idCliente, @RequestBody ClienteDtoPost cliente){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("El cliente se modificó correctamente", clienteServicio.updateCliente(idCliente, cliente)) );
    }

}
