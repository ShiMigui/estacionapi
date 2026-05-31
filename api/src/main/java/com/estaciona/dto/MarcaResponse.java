package com.estaciona.dto;

import com.estaciona.model.Marca;
import com.estaciona.model.interfaces.IResponse;

public record MarcaResponse(Short id, String nome) implements IResponse<Marca> {
  public static MarcaResponse fromEntity(Marca e) {
    return new MarcaResponse(e.id(), e.getNome());
  }
}
