package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.cliente.ClienteDtoGet;
import co.edu.uniquindio.biblioteca.dto.cliente.ClienteDtoPost;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author roaco
 */
@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    /**
     * saveCliente
     * @param cliente
     * @return
     */
    public ClienteDtoGet saveCliente(ClienteDtoPost cliente){
        return convertirClienteAClienteDto(clienteRepo.save(convertirClienteDtoACliente(cliente)));
    }


    /**
     * Busca cliente por ID
     * @param codigoCliente
     * @return
     */
    public ClienteDtoGet findById(Long codigoCliente){
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        return convertirClienteAClienteDto(cliente);
    }

    /**
     * Conversion a ClienteDTO
     * @param cliente
     * @return
     */
    private ClienteDtoGet convertirClienteAClienteDto(Cliente cliente){
        return new ClienteDtoGet(cliente.getCodigo(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    /**
     * Eliminar Cliente
     * @param codigoCliente
     */
    public void deleteCliente(long codigoCliente){
        obtenerCliente(codigoCliente);
        clienteRepo.deleteById(codigoCliente);
    }

    /**
     *Actualizar cliente
     * @param codigoCliente
     * @param clienteNuevo
     * @return
     */
    public ClienteDtoGet updateCliente(long codigoCliente, ClienteDtoPost clienteNuevo){
        obtenerCliente(codigoCliente);
        Cliente nuevo = convertirClienteDtoACliente(clienteNuevo);
        nuevo.setCodigo(codigoCliente);
        return convertirClienteAClienteDto( clienteRepo.save(nuevo) );
    }

    /**
     *Buscar todos
     * @return
     */
    public List<ClienteDtoGet> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertirClienteAClienteDto(c))
                .collect(Collectors.toList());
    }

    /**
     * obtener un cliente
     * @param codigoCliente
     * @return
     */
    public Cliente obtenerCliente(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
    }

    /**
     *Convertir a Cleinte
     * @param cliente
     * @return
     */
    private Cliente convertirClienteDtoACliente(ClienteDtoPost cliente){
        return Cliente.builder()
                .nombre(cliente.nombre())
                .email(cliente.email())
                .telefono(cliente.telefono())
                .password(cliente.password()).build();
    }

    public void validarSiClienteExiste(Long codigoCliente){
        Optional<Cliente> cliente = clienteRepo.findById(codigoCliente);
        if(cliente.isEmpty()){
            throw new ClienteNoEncontradoException("El cliente no existe");
        }
    }

}
