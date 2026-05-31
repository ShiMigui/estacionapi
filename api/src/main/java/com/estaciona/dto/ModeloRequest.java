package com.estaciona.dto;

import com.estaciona.model.Marca;
import com.estaciona.model.Modelo;
import com.estaciona.model.interfaces.IRequest;

public record ModeloRequest(String nome, Short marca_id) implements IRequest<Modelo> {
  @Override
  public Modelo entity() {
    return new Modelo(nome, new Marca(marca_id));
  }
}
