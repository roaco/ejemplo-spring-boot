package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.AutorDto;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AutorServicio {
    private final AutorRepo autorRepo;

    public Autor saveAutor(Autor autor){
        return autorRepo.save(autor);
    }

    public AutorDto findById(long codigoAutor){
        Autor autor = autorRepo.findById(codigoAutor).orElseThrow( () -> new AutorNoEncontradoException("Autor no existe"));
        return convertirAutorAAutorDto(autor);
    }

    private AutorDto convertirAutorAAutorDto(Autor autor){
        return new AutorDto(autor.getIdAutores(), autor.getNombre());
    }

    public void deleteAutor(long idAutor){
        obtenerAutor(idAutor);
        autorRepo.deleteById(idAutor);
    }

    public AutorDto updateAutor(long codigoAutor, AutorDto autorNuevo){
        obtenerAutor(codigoAutor);
        Autor autorN = convertirAutorDtoAAutor(autorNuevo);
        autorN.setIdAutores(codigoAutor);
        return convertirAutorAAutorDto(autorRepo.save(autorN));
    }

    private Autor convertirAutorDtoAAutor(AutorDto autorNuevo) {
        return  Autor.builder()
                .nombre(autorNuevo.nombre())
                .build();
    }

    private Autor obtenerAutor (Long idAutor){
        return autorRepo.findById(idAutor).orElseThrow(() -> new AutorNoEncontradoException("Autor no existe"));
    }

    public List<AutorDto> findAll(){
        return autorRepo.findAll()
                .stream()//Sirve para concatenar atributos e imprimirlos
                .map(a -> convertirAutorAAutorDto(a))
                .collect(Collectors.toList());
    }
}
