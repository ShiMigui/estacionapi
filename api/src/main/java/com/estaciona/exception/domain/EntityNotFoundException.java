package com.estaciona.exception.domain;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Class<?> entityClass, Object id) {
    super(entityClass.getSimpleName() + " not found with id: " + id);
  }
}
