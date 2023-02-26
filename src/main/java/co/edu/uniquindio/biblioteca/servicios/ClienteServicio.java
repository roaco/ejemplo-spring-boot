package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ClienteDtoGet;
import co.edu.uniquindio.biblioteca.dto.ClienteDtoPost;
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

    public ClienteDtoGet saveCliente(ClienteDtoPost cliente){
        return convertirClienteAClienteDto(clienteRepo.save(convertirClienteDtoACliente(cliente)));
    }


    public ClienteDtoGet findById(Long codigoCliente){
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        return convertirClienteAClienteDto(cliente);
    }

    private ClienteDtoGet convertirClienteAClienteDto(Cliente cliente){
        return new ClienteDtoGet(cliente.getCodigo(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    public void deleteCliente(long codigoCliente){
        obtenerCliente(codigoCliente);
        clienteRepo.deleteById(codigoCliente);
    }

    public ClienteDtoGet updateCliente(long codigoCliente, ClienteDtoPost clienteNuevo){
        obtenerCliente(codigoCliente);
        Cliente nuevo = convertirClienteDtoACliente(clienteNuevo);
        nuevo.setCodigo(codigoCliente);
        return convertirClienteAClienteDto( clienteRepo.save(nuevo) );
    }

    public List<ClienteDtoGet> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertirClienteAClienteDto(c))
                .collect(Collectors.toList());
    }

    private Cliente obtenerCliente(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
    }
    private Cliente convertirClienteDtoACliente(ClienteDtoPost cliente){
        return Cliente.builder()
                .nombre(cliente.nombre())
                .email(cliente.email())
                .telefono(cliente.telefono())
                .password(cliente.password()).build();
    }

}
