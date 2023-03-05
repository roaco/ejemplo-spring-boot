package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAutores;
    @Column(nullable = false)
    private String nombre;

    @Builder
    public Autor(String nombre) {
        this.nombre = nombre;
    }

}
