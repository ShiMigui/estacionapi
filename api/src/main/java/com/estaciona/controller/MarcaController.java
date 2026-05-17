package com.estaciona.controller;

import com.estaciona.model.IService;
import com.estaciona.model.Marca;
import com.estaciona.service.JpaMarcaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
  private final IService<Marca, Short> service;

  public MarcaController(JpaMarcaService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Marca>> all() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Marca> find(@PathVariable Short id) {
    return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Marca> create(@RequestBody Marca data) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(data));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Marca> update(@PathVariable Short id, @RequestBody Marca data) {
    data.setId(id);
    return service.update(data).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Short id) {
    return (service.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound()).build();
  }
}
