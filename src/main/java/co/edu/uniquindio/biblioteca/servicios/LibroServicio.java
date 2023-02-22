package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.LibroDto;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroServicio {
    private final LibroRepo libroRepo;

    public Libro saveLibro(Libro libro){
        return libroRepo.save(libro);
    }

//    public LibroDto findLibroById(String isbn){
//        Libro libro = libroRepo.findById(isbn).orElseThrow(() -> new LibroNoEncontradoException("El libro no existe"));
//        return  convertirLibroALibroDto(libro);
//    }

//    private LibroDto convertirLibroALibroDto(Libro libro){
//        return  new LibroDto(libro.getIsbn(), libro.getNombre(), libro.getGenero(), libro.getUnidades(), libro.getFechaPublicacion());
//    }

    public void deleteLibro(String isbn){
        libroRepo.findById(isbn).orElseThrow(() -> new LibroNoEncontradoException("El libro no existe"));
        libroRepo.deleteById(isbn);
    }

    public Libro updateLibro(String isbn, Libro libroNuevo){
        libroRepo.findById(isbn).orElseThrow(() -> new LibroNoEncontradoException("El libro no existe"));
        return libroRepo.save(libroNuevo);
    }

//    public List<LibroDto>findAll(){
//        return libroRepo.findAll()
//                .stream()
//                .map(l -> convertirLibroALibroDto(l))
//                .collect(Collectors.toList());
//    }
}
