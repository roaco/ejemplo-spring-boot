package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ClienteDto;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public Cliente saveCliente(Cliente cliente){
        return clienteRepo.save(cliente);
    }


    public ClienteDto findById(Long codigoCliente){
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        return convertirClienteAClienteDto(cliente);
    }

    private ClienteDto convertirClienteAClienteDto(Cliente cliente){
        return new ClienteDto(cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    public void deleteCliente(long codigoCliente){
        clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        clienteRepo.deleteById(codigoCliente);
    }

    public Cliente updateCliente(long codigoCliente, Cliente clienteNuevo){
        clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        //clienteNuevo.setCodigo(codigoCliente);
        return clienteRepo.save(clienteNuevo);
    }

    public List<ClienteDto> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertirClienteAClienteDto(c))
                .collect(Collectors.toList());
    }

}
