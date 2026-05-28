package com.estaciona.controller;

import com.estaciona.model.AbstractEntity;
import com.estaciona.model.interfaces.IService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

public abstract class SupportController<E extends AbstractEntity<ID>, ID> {
  protected final IService<E, ID> service;

  protected SupportController(IService<E, ID> service) {
    this.service = service;
  }

  protected abstract String getURL();

  protected ResponseEntity<List<E>> all() {
    return ResponseEntity.ok(service.all());
  }

  protected ResponseEntity<E> find(ID id) {
    return ResponseEntity.ok(service.findById(id));
  }

  protected ResponseEntity<E> create(E data) {
    E saved = service.create(data);
    URI uri = URI.create(getURL() + "/" + saved.getId());
    return ResponseEntity.created(uri).body(saved);
  }

  protected ResponseEntity<E> update(ID id, E data) {
    data.changeId(id);
    return ResponseEntity.ok(service.update(data));
  }

  protected ResponseEntity<Void> delete(ID id) {
    return (service.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound()).build();
  }
}
