package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers.exceptions;

import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.*;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> genericException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Param is required";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getRequestURI());
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
    public ResponseEntity<StandardError> loanLimitException(LoanLimitException e, HttpServletRequest request) {
        String error = "Loan limit exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoanSameBookException.class)
    public ResponseEntity<StandardError> loanSameBookException(LoanSameBookException e, HttpServletRequest request) {
        String error = "Loan same book exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MinimumQuantityBookException.class)
    public ResponseEntity<StandardError> minimumQuantityBookException(MinimumQuantityBookException e, HttpServletRequest request) {
        String error = "Minimum quantity of book exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotPaidException.class)
    public ResponseEntity<StandardError> notPaidException(NotPaidException e, HttpServletRequest request) {
        String error = "Not paid exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PendingLoanException.class)
    public ResponseEntity<StandardError> pendingLoanException(PendingLoanException e, HttpServletRequest request) {
        String error = "Pending loan exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BookHasAlreadyBeenReturnedException.class)
    public ResponseEntity<StandardError> bookHasAlreadyBeenReturnedException(BookHasAlreadyBeenReturnedException e, HttpServletRequest request) {
        String error = "book has already been returned exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoanAlreadyPaidException.class)
    public ResponseEntity<StandardError> loanAlreadyPaidException(LoanAlreadyPaidException e, HttpServletRequest request) {
        String error = "loan already paid exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(LoanWithoutDebtsException.class)
    public ResponseEntity<StandardError> loanWithoutDebtsException(LoanWithoutDebtsException e, HttpServletRequest request) {
        String error = "loan without debts exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> IllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        String error = "Param null exception.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
