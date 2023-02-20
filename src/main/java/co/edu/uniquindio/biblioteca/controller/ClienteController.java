package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.ClienteDto;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente){
        return clienteServicio.saveCliente(cliente);
    }

    @GetMapping("/{idCliente}")
    public ClienteDto findById(@PathVariable long idCliente){
        return clienteServicio.findById(idCliente);
    }

    @GetMapping
    public List<ClienteDto> findAll(){
        return clienteServicio.findAll();
    }

    @DeleteMapping("/{idCliente}")
    public String delete(@PathVariable long idCliente){
        clienteServicio.deleteCliente(idCliente);
        return "Se eliminó cliente";
    }


    @PutMapping("/{idCliente}")
    public Cliente update(@PathVariable long idCliente, @RequestBody Cliente cliente){
        return clienteServicio.updateCliente(idCliente, cliente);
    }

}
