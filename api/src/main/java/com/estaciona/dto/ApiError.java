package com.estaciona.dto;

import org.springframework.http.HttpStatus;

public record ApiError(int status, String error, String message) {
  public static ApiError fromHTTP(HttpStatus http, String message) {
    return new ApiError(http.value(), http.getReasonPhrase(), message);
  }
}
