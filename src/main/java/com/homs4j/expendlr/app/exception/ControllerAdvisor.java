package com.homs4j.expendlr.app.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("type","ARGUMENTS ARE MISSING");
        body.put("message",ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        body.put("path",request.getDescription(false).substring(4));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(EntityNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.NOT_FOUND.value());
        body.put("type","ENTITY INFORMATION NOT FOUND");
        body.put("message", ex.getMessage());
        body.put("path",request.getDescription(false).substring(4));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotCreatedException.class)
    public ResponseEntity<Object> handleDataNotCreatedException(EntityNotCreatedException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("type","ENTITY INFORMATION NOT SAVED");
        body.put("message", ex.getMessage());
        body.put("path",request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotUpdatedException.class)
    public ResponseEntity<Object> handleDataNotUpdatedException(EntityNotUpdatedException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("type","ENTITY INFORMATION NOT UPDATED");
        body.put("message", ex.getMessage());
        body.put("path",request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
