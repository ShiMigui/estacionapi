package com.estaciona.exception.domain;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entityClass, Object id) {
    super(entityClass.getSimpleName() + " não encontrado com o id " + id);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
