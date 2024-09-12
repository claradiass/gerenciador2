package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers.exceptions;

import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.*;
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

    @ExceptionHandler(LoanLimitException.class)
    public ResponseEntity<StandardError> LoanLimitException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Loan limit exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoanSameBookException.class)
    public ResponseEntity<StandardError> LoanSameBookException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Loan same book exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MinimumQuantityBookException.class)
    public ResponseEntity<StandardError> MinimumQuantityBookException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Minimum quantity of book exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotPaidException.class)
    public ResponseEntity<StandardError> NotPaidException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Not paid exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PendingLoanException.class)
    public ResponseEntity<StandardError> PendingLoanException(AlreadyExistsException e, HttpServletRequest request) {
        String error = "Pending loan exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
