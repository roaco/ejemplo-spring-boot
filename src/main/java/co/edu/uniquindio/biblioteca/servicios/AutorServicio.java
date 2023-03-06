package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.autor.AutorDto;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Autor: roaco
 */
@Service
@AllArgsConstructor
public class AutorServicio {
    private final AutorRepo autorRepo;

    /**
     *  Constructor
     * @param autor
     * @return
     */

    public Autor saveAutor(Autor autor){
        return autorRepo.save(autor);
    }

    /**
     *
     * @param codigoAutor
     * @return
     */
    public AutorDto findById(long codigoAutor){
        Autor autor = autorRepo.findById(codigoAutor).orElseThrow( () -> new AutorNoEncontradoException("Autor no existe"));
        return convertirAutorAAutorDto(autor);
    }

    /**
     *
     * @param autor
     * @return
     */

    private AutorDto convertirAutorAAutorDto(Autor autor){
        return new AutorDto(autor.getIdAutores(), autor.getNombre());
    }

    /**
     *
     * @param idAutor
     */

    public void deleteAutor(long idAutor){
        obtenerAutor(idAutor);
        autorRepo.deleteById(idAutor);
    }

    /**
     *
     * @param codigoAutor
     * @param autorNuevo
     * @return
     */

    public AutorDto updateAutor(long codigoAutor, AutorDto autorNuevo){
        obtenerAutor(codigoAutor);
        Autor autorN = convertirAutorDtoAAutor(autorNuevo);
        autorN.setIdAutores(codigoAutor);
        return convertirAutorAAutorDto(autorRepo.save(autorN));
    }

    /**
     *
     * @param autorNuevo
     * @return
     */

    private Autor convertirAutorDtoAAutor(AutorDto autorNuevo) {
        return  Autor.builder()
                .nombre(autorNuevo.nombre())
                .build();
    }

    /**
     *
     * @param idAutor
     * @return
     */
    private Autor obtenerAutor (Long idAutor){
        return autorRepo.findById(idAutor).orElseThrow(() -> new AutorNoEncontradoException("Autor no existe"));
    }

    /**
     *
     * @return
     */
    public List<AutorDto> findAll(){
        return autorRepo.findAll()
                .stream()//Sirve para concatenar atributos e imprimirlos
                .map(a -> convertirAutorAAutorDto(a))
                .collect(Collectors.toList());
    }
}
