package com.estaciona.controller;

import com.estaciona.model.enums.Cor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cores")
public class CorController {
  @GetMapping
  public Cor[] listar() {
    return Cor.values();
  }
}
