package co.edu.uniquindio.biblioteca.repo;

import co.edu.uniquindio.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepo extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
}
