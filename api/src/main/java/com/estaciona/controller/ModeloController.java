package com.estaciona.controller;

import com.estaciona.dto.ModeloRequest;
import com.estaciona.dto.ModeloResponse;
import com.estaciona.model.Modelo;
import com.estaciona.service.ModeloService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelos")
public class ModeloController
    extends SupportController<Modelo, Short, ModeloRequest, ModeloResponse> {
  public ModeloController(ModeloService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<ModeloResponse>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ModeloResponse> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<ModeloResponse> create(@RequestBody ModeloRequest data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ModeloResponse> update(
      @PathVariable Short id, @RequestBody ModeloRequest data) {
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

  @Override
  protected ModeloResponse toResponse(Modelo e) {
    return ModeloResponse.fromEntity(e);
  }
}
