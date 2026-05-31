package com.estaciona.controller;

import com.estaciona.dto.CarroRequest;
import com.estaciona.dto.CarroResponse;
import com.estaciona.model.Carro;
import com.estaciona.service.CarroService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarroController extends SupportController<Carro, Long, CarroRequest, CarroResponse> {
  public CarroController(CarroService service) {
    super(service);
  }

  @GetMapping
  public ResponseEntity<List<CarroResponse>> all() {
    return super.all();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarroResponse> find(@PathVariable Long id) {
    return super.find(id);
  }

  @PostMapping
  public ResponseEntity<CarroResponse> create(@RequestBody CarroRequest data) {
    return super.create(data);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarroResponse> update(
      @PathVariable Long id, @RequestBody CarroRequest data) {
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

  @Override
  protected CarroResponse toResponse(Carro e) {
    return CarroResponse.fromEntity(e);
  }
}
