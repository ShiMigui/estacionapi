package com.estaciona.model;

import java.util.List;
import java.util.Optional;

public interface IService<E, T> {
  public Optional<E> findById(T id);

  public E save(E obj);

  public List<E> getAll();

  public Optional<E> update(E newData);

  public boolean delete(T id);
}
