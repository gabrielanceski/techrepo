package br.edu.ifrs.gabrielanceski.techrepo.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(field -> field.getField() + " " + field.getDefaultMessage()).collect(Collectors.toList());

        ErrorObject errorObject = new ErrorObject(
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            errors.toArray(new String[errors.size()])
        );
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUserNotFoundExceptions(UsernameNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorObject> handleRuntimeExceptions(RuntimeException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleResourceAlreadyExistsExceptions(ResourceAlreadyExistsException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.CONFLICT.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundExceptions(ResourceNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorObject> handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorObject> handleHttpMethodNotSupportedExceptions(HttpRequestMethodNotSupportedException ex) {
        ErrorObject errorObject = new ErrorObject(
            HttpStatus.NOT_IMPLEMENTED.value(),
            LocalDateTime.now(),
            ex.getMessage()
        );
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_IMPLEMENTED);
    }
}
