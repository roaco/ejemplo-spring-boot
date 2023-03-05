package co.edu.uniquindio.biblioteca.servicios.excepciones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorInfo {
    @JsonProperty("mensaje")
    private String mensaje;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonProperty("uri")
    private String uriRequested;
    @JsonProperty("localDateTime")
    private LocalDateTime localDateTime;


    public ErrorInfo(int statusCode, String message, String uriRequested, LocalDateTime localDateTime) {
        this.mensaje = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
        this.localDateTime = localDateTime;
    }
}
