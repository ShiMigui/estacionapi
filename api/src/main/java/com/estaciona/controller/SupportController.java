package com.estaciona.controller;

import com.estaciona.model.AbstractEntity;
import com.estaciona.model.interfaces.IRequest;
import com.estaciona.model.interfaces.IResponse;
import com.estaciona.model.interfaces.IService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public abstract class SupportController<
    E extends AbstractEntity<ID>, ID, Rq extends IRequest<E>, Res extends IResponse<E>> {
  protected final IService<E, ID> service;

  protected abstract String getURL();

  public SupportController(IService<E, ID> service) {
    this.service = service;
  }

  protected ResponseEntity<List<Res>> all() {
    return ok(listToResponse(service.all()));
  }

  protected ResponseEntity<Res> find(ID id) {
    return ok(toResponse(service.findById(id)));
  }

  protected ResponseEntity<Res> create(Rq data) {
    return this.create(data.entity());
  }

  protected ResponseEntity<Res> create(E entity) {
    E saved = service.create(entity);
    return created(toResponse(saved), uri(saved.getId()));
  }

  protected ResponseEntity<Res> update(ID id, Rq data) {
    E e = data.entity();
    e.changeId(id);
    return ok(toResponse(service.update(e)));
  }

  protected ResponseEntity<Void> delete(ID id) {
    return (service.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound()).build();
  }

  protected URI uri(Object value) {
    return URI.create(getURL() + "/" + value);
  }

  protected <T> ResponseEntity<T> ok(T value) {
    return ResponseEntity.ok(value);
  }

  protected <T> ResponseEntity<T> created(T value, URI uri) {
    return ResponseEntity.created(uri).body(value);
  }

  protected List<Res> listToResponse(List<E> list) {
    return list.stream().map(e -> toResponse(e)).toList();
  }

  protected abstract Res toResponse(E e);
}
