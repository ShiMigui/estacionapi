package com.estaciona.exception;

import com.estaciona.dto.ApiError;
import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.exception.domain.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  public static Boolean devContext = null;
  private final Environment env;

  public GlobalExceptionHandler(Environment env) {
    this.env = env;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
    return httpResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler({ValidationException.class, IllegalArgumentException.class})
  public ResponseEntity<ApiError> handleValidation(RuntimeException ex) {
    return httpResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleInvalidJson(HttpMessageNotReadableException ex) {
    return httpResponse(HttpStatus.BAD_REQUEST, "JSON inválido ou formato de dados incorreto");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception ex) {
    log.error("Unexpected error", ex);

    String message =
        isDevContext()
            ? "[DEV] " + ex.getClass().getSimpleName() + ": " + ex.getMessage()
            : "Ocorreu um erro interno no servidor";

    return httpResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  public ResponseEntity<ApiError> httpResponse(HttpStatus http, String message) {
    return ResponseEntity.status(http).body(ApiError.fromHTTP(http, message));
  }

  private boolean isDevContext() {
    if (devContext == null) {
      for (String p : env.getActiveProfiles()) {
        if (p.trim().toLowerCase().equals("dev")) {
          devContext = true;
          break;
        }
      }
    }
    return devContext;
  }
}
