package co.edu.uniquindio.biblioteca.repo;

import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepo extends JpaRepository<Libro, String> {
//    Optional<Libro> findByAutor(Autor autor);
//    Optional<Libro> findByNombre(String nombre);
}
