package com.estaciona.controller;

import com.estaciona.model.IService;
import com.estaciona.model.Modelo;
import com.estaciona.service.JpaModeloService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelos")
public class ModeloController {
  private final IService<Modelo, Short> service;

  public ModeloController(JpaModeloService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Modelo>> allMarcas() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Modelo> find(@PathVariable Short id) {
    return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Modelo> createMarca(@RequestBody Modelo data) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(data));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Modelo> updateUser(@PathVariable Short id, @RequestBody Modelo data) {
    return service.update(data).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMarca(@PathVariable Short id) {
    return (service.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound()).build();
  }
}
