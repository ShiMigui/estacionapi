package com.estaciona.service;

import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.model.AbstractEntity;
import com.estaciona.model.IService;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class JpaService<E extends AbstractEntity<ID>, ID> implements IService<E, ID> {
  protected final JpaRepository<E, ID> repo;
  protected final Class<E> entityClass;

  public JpaService(JpaRepository<E, ID> repo, Class<E> entityClass) {
    this.repo = repo;
    this.entityClass = entityClass;
  }

  public E findById(ID id) {
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(entityClass, id));
  }

  public E save(E obj) {
    return repo.save(obj);
  }

  public List<E> getAll() {
    return repo.findAll();
  }

  public E update(ID id, Function<? super E, ? extends E> mapper) {
    return repo.save(mapper.apply(findById(id)));
  }

  public boolean delete(ID id) {
    if (!repo.existsById(id)) {
      return false;
    }
    repo.deleteById(id);
    return true;
  }
}
