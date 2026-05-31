package com.estaciona.controller;

import com.estaciona.dto.ClienteRequest;
import com.estaciona.dto.ClienteResponse;
import com.estaciona.model.Cliente;
import com.estaciona.service.ClienteService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController
    extends SupportController<Cliente, Long, ClienteRequest, ClienteResponse> {
  public ClienteController(ClienteService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<ClienteResponse>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> find(@PathVariable Long id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> update(
      @PathVariable Long id, @RequestBody ClienteRequest data) {
    return super.update(id, data);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return super.delete(id);
  }

  @Override
  protected String getURL() {
    return "/clientes";
  }

  @Override
  protected ClienteResponse toResponse(Cliente e) {
    return ClienteResponse.fromEntity(e);
  }
}
