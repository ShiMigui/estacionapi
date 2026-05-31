package com.estaciona.dto;

import com.estaciona.model.Preco;
import com.estaciona.model.interfaces.IRequest;

public record PrecoRequest(String nome, Double preco, String descricao) implements IRequest<Preco> {
  @Override
  public Preco entity() {
    return new Preco(nome, preco, descricao);
  }
}
