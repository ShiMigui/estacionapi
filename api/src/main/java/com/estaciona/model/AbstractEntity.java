package com.estaciona.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<ID> {
  public abstract ID getId();

  protected abstract void internalSetId(ID id);

  public final void setId(ID id) {
    ID current = getId();

    if (current != null) {
      if (current.equals(id)) return;

      throw new IllegalArgumentException("ID já foi definido e não pode ser alterado");
    }

    internalSetId(id);
  }
}
