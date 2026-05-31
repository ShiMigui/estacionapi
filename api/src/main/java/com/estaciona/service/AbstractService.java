package com.estaciona.service;

import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.model.AbstractEntity;
import com.estaciona.model.interfaces.IRepository;
import com.estaciona.model.interfaces.IService;
import jakarta.validation.ValidationException;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractService<E extends AbstractEntity<ID>, ID> implements IService<E, ID> {
  protected final IRepository<E, ID> repo;
  protected final Class<E> entityClass;

  public AbstractService(IRepository<E, ID> repo, Class<E> entityClass) {
    this.repo = repo;
    this.entityClass = entityClass;
  }

  public E create(E obj) {
    return repo.save(obj);
  }

  public E findById(ID id) {
    return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(entityClass, id));
  }

  public E findWith(E e) {
    if (e == null) throw new ValidationException(entityClass + " passada é nula!");
    return findById(e.id());
  }

  public List<E> all() {
    return repo.findAll();
  }

  public E update(ID id, Function<? super E, ? extends E> mapper) {
    E obj = mapper.apply(findById(id));
    repo.save(obj);
    return obj;
  }

  public boolean delete(ID id) {
    if (!repo.existsById(id)) return false;
    repo.deleteById(id);
    return true;
  }
}
