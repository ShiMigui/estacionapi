package com.estaciona.controller;

import com.estaciona.model.Modelo;
import com.estaciona.service.ModeloService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelos")
public class ModeloController extends SupportController<Modelo, Short> {
  public ModeloController(ModeloService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Modelo>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Modelo> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<Modelo> create(@RequestBody Modelo data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Modelo> update(@PathVariable Short id, @RequestBody Modelo data) {
    return super.update(id, data);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Short id) {
    return super.delete(id);
  }

  @Override
  protected String getURL() {
    return "/modelos";
  }
}
