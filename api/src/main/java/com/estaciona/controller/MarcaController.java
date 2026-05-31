package com.estaciona.controller;

import com.estaciona.dto.MarcaRequest;
import com.estaciona.dto.MarcaResponse;
import com.estaciona.model.Marca;
import com.estaciona.service.MarcaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marcas")
public class MarcaController extends SupportController<Marca, Short, MarcaRequest, MarcaResponse> {
  public MarcaController(MarcaService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<MarcaResponse>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<MarcaResponse> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<MarcaResponse> create(@RequestBody MarcaRequest data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MarcaResponse> update(
      @PathVariable Short id, @RequestBody MarcaRequest data) {
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

  @Override
  protected MarcaResponse toResponse(Marca e) {
    return MarcaResponse.fromEntity(e);
  }
}
