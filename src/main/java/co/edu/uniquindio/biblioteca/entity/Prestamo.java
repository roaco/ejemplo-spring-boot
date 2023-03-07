package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(nullable = false)
    private LocalDateTime fechaDevolucion;

    @Column(nullable = false)
    private boolean estaActivo;

    @ManyToMany
    private List<Libro> libros;
}
