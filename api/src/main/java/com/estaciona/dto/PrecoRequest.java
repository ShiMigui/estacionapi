package com.estaciona.dto;

import com.estaciona.model.Preco;
import com.estaciona.model.interfaces.IRequest;

public record PrecoRequest(String nome, Double preco, String descricao) implements IRequest<Preco> {
  @Override
  public Preco entity() {
    Preco e = new Preco();
    e.changeNome(nome);
    e.changePreco(preco);
    e.setDescricao(descricao == null ? "" : descricao);
    return e;
  }
}
