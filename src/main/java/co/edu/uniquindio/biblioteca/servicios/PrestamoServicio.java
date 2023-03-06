package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.prestamo.PrestamoDto;
import co.edu.uniquindio.biblioteca.dto.prestamo.PrestamoGetDto;
import co.edu.uniquindio.biblioteca.entity.*;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.repo.PrestamoRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.PrestamoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrestamoServicio {
    public final ClienteRepo clienteRepo;
    public final ClienteServicio clienteServicio;
    private final LibroRepo libroRepo;
    public final PrestamoRepo prestamoRepo;

    /**
     * Eliminar prestamo
     *
     * @param codigo
     */
    public void delete(long codigo) {
        obtenerPrestamo(codigo);
        prestamoRepo.deleteById(codigo);
    }

    /**
     * Obtener un prestamo
     *
     * @param codigo
     * @return
     */
    private Prestamo obtenerPrestamo(Long codigo) {
        return prestamoRepo.findById(codigo).orElseThrow(() -> new PrestamoNoEncontradoException("El prestamo no existe"));
    }

    /**
     * Buscar todos los prestamos
     *
     * @return
     */
    public List<PrestamoGetDto> findAll() {
        return prestamoRepo.findAll().stream().map(this::convertirPrestamoAPrestamoGetDto).toList();
    }

    /**
     * Convertir prestamo a prestamoDto
     *
     * @param prestamo
     * @return
     */
    public PrestamoGetDto convertirPrestamoAPrestamoGetDto(Prestamo prestamo) {
        return new PrestamoGetDto(prestamo.getCodigo(), prestamo.getCliente().getCodigo(), prestamo.getLibros().stream().map(Libro::getIsbn).toList(), prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion());
    }

    public PrestamoDto convertirPrestamoAPrestamoDto(Prestamo prestamo) {
        List<String> isbnLibros = new ArrayList<String>();

        for (Libro libro : prestamo.getLibros()) {
            isbnLibros.add(libro.getIsbn());
        }
        return new PrestamoDto(prestamo.getCodigo(), isbnLibros, prestamo.getFechaDevolucion());
    }


    /**
     * buscar por codigo del prestamo
     *
     * @param codigo
     * @return LoanGetDTO
     */
    public PrestamoGetDto findById(long codigo) {
        return convertirPrestamoAPrestamoGetDto(obtenerPrestamo(codigo));
    }


    /**
     * Guardar prestamo
     * @param prestamoDto
     * @return
     */
    public PrestamoDto save(PrestamoDto prestamoDto) {
        long clienteId = prestamoDto.clienteId();

        clienteServicio.validarSiClienteExiste(clienteId);

        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(clienteServicio.obtenerCliente(clienteId));
        prestamo.setFechaPrestamo(LocalDateTime.now());
        List<String> isbnLibros = prestamoDto.isbnLibros();
        List<Libro> libros = new ArrayList<>();

        for (String isbnLibro : isbnLibros) {
            Optional<Libro> libro = libroRepo.findById(isbnLibro);

            if (libro.isEmpty()) {
                throw new LibroNoEncontradoException("El libro no existe");
            }

            libros.add(libro.get());
        }
        prestamo.setLibros(libros);
        prestamo.setFechaDevolucion(prestamoDto.fechaDevolucion());

        return convertirPrestamoAPrestamoDto(prestamoRepo.save(prestamo));
    }


    /**
     * Actualizar prestamo
     * @param codigoPrestamo
     * @param prestamoDto
     * @return
     */
    public PrestamoDto update(long codigoPrestamo, PrestamoDto prestamoDto) {
        obtenerPrestamo(codigoPrestamo);
        Prestamo prestamo = validarPrestamo(prestamoDto, codigoPrestamo);
        return convertirPrestamoAPrestamoDto(prestamoRepo.save(prestamo));
    }

    /**
     * validar un prestamo
     * @param prestamoDto
     * @param codigoPrestamo
     * @return
     */
    public Prestamo validarPrestamo(PrestamoDto prestamoDto, long codigoPrestamo){
        List<Libro> libros = libroRepo.findAllById(prestamoDto.isbnLibros());
        Optional<Cliente> cliente = clienteRepo.findById(prestamoDto.clienteId());

        if (libros.size() != prestamoDto.isbnLibros().size()) {
            throw new LibroNoEncontradoException("Hay libros que no existen en la base de datos");
        }

        return Prestamo.builder().codigo(codigoPrestamo).cliente(cliente.get()).fechaPrestamo(LocalDateTime.now()).fechaDevolucion(prestamoDto.fechaDevolucion()).libros(libros).build();
    }
}
