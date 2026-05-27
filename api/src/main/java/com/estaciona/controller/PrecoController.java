package com.estaciona.controller;

import com.estaciona.model.Preco;
import com.estaciona.service.JpaPrecoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/precos")
public class PrecoController extends SupportController<Preco, Short> {
  public PrecoController(JpaPrecoService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Preco>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Preco> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<Preco> create(@RequestBody Preco data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Preco> update(@PathVariable Short id, @RequestBody Preco data) {
    return super.update(id, data);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Short id) {
    return super.delete(id);
  }

  @Override
  protected String getURL() {
    return "/precos";
  }
}
