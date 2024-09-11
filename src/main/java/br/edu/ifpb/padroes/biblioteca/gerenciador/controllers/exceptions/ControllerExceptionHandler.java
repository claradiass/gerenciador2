package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers.exceptions;

import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> genericNotFoundException(NotFoundException e, HttpServletRequest request) {
        String error = "Object not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> alreadyExistsException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Object already exists";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
