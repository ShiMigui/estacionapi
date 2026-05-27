package com.estaciona.controller;

import com.estaciona.model.Carro;
import com.estaciona.service.CarroService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarroController extends SupportController<Carro, Long> {
  public CarroController(CarroService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Carro>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Carro> find(@PathVariable Long id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<Carro> create(@RequestBody Carro data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody Carro data) {
    return super.update(id, data);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return super.delete(id);
  }

  @Override
  protected String getURL() {
    return "/carros";
  }
}
