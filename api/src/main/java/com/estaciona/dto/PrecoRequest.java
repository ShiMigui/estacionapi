package com.estaciona.dto;

import com.estaciona.model.Preco;
import com.estaciona.model.interfaces.IRequest;

public record PrecoRequest(String nome, Double preco, String descricao) implements IRequest<Preco> {
  @Override
  public Preco entity() {
    Preco e = new Preco();
    if (nome != null) e.changeNome(nome);
    if (preco != null) e.changePreco(preco);
    if (descricao != null) e.setDescricao(descricao);
    return e;
  }
}
