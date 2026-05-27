package com.estaciona.controller;

import com.estaciona.model.Entrada;
import com.estaciona.model.id.EntradaId;
import com.estaciona.service.JpaEntradaService;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entradas")
public class EntradaController extends SupportController<Entrada, EntradaId> {
  public EntradaController(JpaEntradaService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Entrada>> all() {
    return super.all();
  }

  @GetMapping("/{carroId}")
  public ResponseEntity<Entrada> find(
      @PathVariable Long carroId, @RequestParam OffsetDateTime entrada) {
    return super.find(new EntradaId(carroId, entrada));
  }

  @PostMapping
  public ResponseEntity<Entrada> create(@RequestBody Entrada data) {
    return super.create(data);
  }

  @PutMapping("/{carroId}")
  public ResponseEntity<Entrada> update(
      @PathVariable Long carroId, @RequestParam OffsetDateTime entrada, @RequestBody Entrada data) {
    data.setId(new EntradaId(carroId, entrada));

    return super.update(data.getId(), data);
  }

  @DeleteMapping("/{carroId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long carroId, @RequestParam OffsetDateTime entrada) {
    return super.delete(new EntradaId(carroId, entrada));
  }

  @Override
  protected String getURL() {
    return "/entradas";
  }
}
