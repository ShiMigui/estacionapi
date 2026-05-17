package com.estaciona.controller;

import com.estaciona.model.Marca;
import com.estaciona.service.JpaMarcaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marcas")
public class MarcaController extends SupportController<Marca, Short> {
  public MarcaController(JpaMarcaService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Marca>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Marca> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<Marca> create(@RequestBody Marca data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Marca> update(@PathVariable Short id, @RequestBody Marca data) {
    return super.update(id, data);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Short id) {
    return super.delete(id);
  }

  @Override
  protected String getURL() {
    return "/marcas";
  }
}
