package com.estaciona.exception;

import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.exception.domain.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

  private Map<String, Object> buildBody(
      HttpStatus status, String message, HttpServletRequest request) {
    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", status.value());
    body.put("error", status.getReasonPhrase());
    body.put("message", message);
    body.put("path", request.getRequestURI());

    return body;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public Map<String, Object> handleEntityNotFound(
      EntityNotFoundException ex, HttpServletRequest request) {
    return buildBody(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  @ExceptionHandler(ValidationException.class)
  public Map<String, Object> handleValidation(ValidationException ex, HttpServletRequest request) {
    return buildBody(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, Object> handleBeanValidation(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, Object> body = buildBody(HttpStatus.BAD_REQUEST, "Erro de validação", request);

    Map<String, String> errors = new HashMap<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }

    body.put("fields", errors);

    return body;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public Map<String, Object> handleConstraintViolation(
      ConstraintViolationException ex, HttpServletRequest request) {
    return buildBody(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public Map<String, Object> handleDataIntegrity(
      DataIntegrityViolationException ex, HttpServletRequest request) {
    return buildBody(HttpStatus.CONFLICT, "Violação de integridade de dados", request);
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  public Map<String, Object> handleSerialization(
      HttpMessageNotWritableException ex, HttpServletRequest request) {
    return buildBody(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao serializar JSON", request);
  }

  @ExceptionHandler(Exception.class)
  public Map<String, Object> handleGeneric(Exception ex, HttpServletRequest request) {
    return buildBody(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
  }
}
