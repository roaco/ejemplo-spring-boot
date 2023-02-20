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
        return new AutorDto(autor.getId(), autor.getNombre());
    }

    public void deleteAutor(long codigoAutor){
        autorRepo.findById(codigoAutor).orElseThrow(() -> new AutorNoEncontradoException("Autor no existe"));
        autorRepo.deleteById(codigoAutor);
    }

    public Autor updateAutor(long codigoAutor, Autor autorNuevo){
        autorRepo.findById(codigoAutor).orElseThrow(() -> new AutorNoEncontradoException("Autor no existe"));
        return autorRepo.save(autorNuevo);
    }

    public List<AutorDto> findAll(){
        return autorRepo.findAll()
                .stream()//Sirve para concatenar atributos e imprimirlos
                .map(a -> convertirAutorAAutorDto(a))
                .collect(Collectors.toList());
    }
}
