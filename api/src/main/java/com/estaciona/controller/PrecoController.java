package com.estaciona.controller;

import com.estaciona.dto.PrecoRequest;
import com.estaciona.dto.PrecoResponse;
import com.estaciona.model.Preco;
import com.estaciona.service.PrecoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/precos")
public class PrecoController extends SupportController<Preco, Short, PrecoRequest, PrecoResponse> {
  public PrecoController(PrecoService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<PrecoResponse>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PrecoResponse> find(@PathVariable Short id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<PrecoResponse> create(@RequestBody PrecoRequest data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PrecoResponse> update(
      @PathVariable Short id, @RequestBody PrecoRequest data) {
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

  @Override
  protected PrecoResponse toResponse(Preco e) {
    return PrecoResponse.fromEntity(e);
  }
}
