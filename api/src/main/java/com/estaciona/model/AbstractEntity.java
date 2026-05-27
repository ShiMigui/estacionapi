package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<ID> {
  public abstract ID getId();

  protected abstract void internalSetId(ID id);

  public ID id() {
    ID id = getId();
    if (id == null) throw new ValidationException("ID must not be null!");
    return id;
  }

  public void setId(ID id) {
    ID current = getId();
    if (current != null) {
      if (current.equals(id)) return;
      throw new ValidationException("ID já foi definido e não pode ser alterado");
    }
    internalSetId(id);
  }
}
