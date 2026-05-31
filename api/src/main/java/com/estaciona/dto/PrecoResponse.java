package com.estaciona.dto;

import com.estaciona.model.Preco;
import com.estaciona.model.interfaces.IResponse;

public record PrecoResponse(Short id, String nome, Double preco, String descricao)
    implements IResponse<Preco> {

  public static PrecoResponse fromEntity(Preco e) {
    return new PrecoResponse(e.id(), e.getNome(), e.getPreco().doubleValue(), e.getDescricao());
  }
}
