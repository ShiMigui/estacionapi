package com.estaciona.service;

import com.estaciona.model.IService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class JpaService<E, T> implements IService<E, T> {
  protected final JpaRepository<E, T> repo;

  public JpaService(JpaRepository<E, T> repo) {
    this.repo = repo;
  }

  public Optional<E> findById(T id) {
    return repo.findById(id);
  }

  public E save(E marca) {
    return repo.save(marca);
  }

  public List<E> getAll() {
    return repo.findAll();
  }

  public Optional<E> update(T id, Function<? super E, ? extends E> mapper) {
    return repo.findById(id).map(mapper).map(repo::save);
  }

  public boolean delete(T id) {
    if (!repo.existsById(id)) {
      return false;
    }

    repo.deleteById(id);
    return true;
  }
}
