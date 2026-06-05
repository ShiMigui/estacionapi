package com.estaciona.controller;

import com.estaciona.dto.EntradaRequest;
import com.estaciona.dto.EntradaResponse;
import com.estaciona.dto.SaidaEntradaRequest;
import com.estaciona.model.Entrada;
import com.estaciona.model.id.EntradaId;
import com.estaciona.service.EntradaService;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entradas")
public class EntradaController
    extends SupportController<Entrada, EntradaId, EntradaRequest, EntradaResponse> {
  public EntradaController(EntradaService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<EntradaResponse>> all(@RequestParam(required = false) Boolean ativas) {
    if (Boolean.TRUE.equals(ativas) || ativas == null)
      return ok(listToResponse(((EntradaService) service).findBySaidaIsNull()));
    return super.all();
  }

  @GetMapping("/{carroId}")
  public ResponseEntity<EntradaResponse> find(
      @PathVariable Long carroId, @RequestParam OffsetDateTime entrada) {
    return super.find(new EntradaId(carroId, entrada));
  }

  @PostMapping("/{carroId}")
  public ResponseEntity<EntradaResponse> create(
      @PathVariable Long carroId, @RequestBody EntradaRequest data) {
    Entrada e = data.entity();
    e.changeId(new EntradaId(carroId, OffsetDateTime.now()));
    return super.create(e);
  }

  @PutMapping("/{carroId}")
  public ResponseEntity<EntradaResponse> update(
      @PathVariable Long carroId,
      @RequestParam OffsetDateTime entrada,
      @RequestBody EntradaRequest data) {
    return super.update(new EntradaId(carroId, entrada), data);
  }

  @DeleteMapping("/{carroId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long carroId, @RequestParam OffsetDateTime entrada) {
    return super.delete(new EntradaId(carroId, entrada));
  }

  @PatchMapping("/{carroId}/saida")
  public ResponseEntity<EntradaResponse> registrarSaida(
      @PathVariable Long carroId, @RequestBody SaidaEntradaRequest req) {
    return ok(toResponse(service().registrarSaida(carroId, req.saida())));
  }

  protected EntradaService service() {
    return (EntradaService) service;
  }

  @Override
  protected String getURL() {
    return "/entradas";
  }

  @Override
  protected EntradaResponse toResponse(Entrada e) {
    return EntradaResponse.fromEntity(e);
  }
}
