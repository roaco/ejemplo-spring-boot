package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Autor {

    @Id
    private long id;
    @Column(nullable = false)
    private String nombre;

}
