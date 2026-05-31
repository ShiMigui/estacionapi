package com.estaciona.dto;

import com.estaciona.model.Marca;
import com.estaciona.model.interfaces.IRequest;

public record MarcaRequest(String nome) implements IRequest<Marca> {
  @Override
  public Marca entity() {
    return new Marca(nome);
  }
}
