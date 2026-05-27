package com.estaciona.controller;

import com.estaciona.model.Cliente;
import com.estaciona.service.ClienteService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends SupportController<Cliente, Long> {
  public ClienteController(ClienteService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> find(@PathVariable Long id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<Cliente> create(@RequestBody Cliente data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente data) {
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
}
