package co.edu.uniquindio.biblioteca.servicios.excepciones;


import co.edu.uniquindio.biblioteca.dto.Respuesta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handlerGenericException(HttpServletRequest request, Exception e){
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
