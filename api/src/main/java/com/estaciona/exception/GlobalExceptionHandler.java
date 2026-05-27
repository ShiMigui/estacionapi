package com.estaciona.exception;

import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.exception.domain.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @Value("${app.context:prod}")
  public String appContext;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    String message =
        isDevContext() ? "An error occurred: " + ex.getMessage() : "Internal Error Occurred";
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleNotFoundEntity(EntityNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handleException(ValidationException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  public boolean isDevContext() {
    return "dev".equals(appContext);
  }
}
