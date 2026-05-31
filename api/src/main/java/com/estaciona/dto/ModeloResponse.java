package com.estaciona.dto;

import com.estaciona.model.Modelo;
import com.estaciona.model.interfaces.IResponse;

public record ModeloResponse(Short id, String nome, MarcaResponse marca)
    implements IResponse<Modelo> {

  public static ModeloResponse fromEntity(Modelo e) {
    return new ModeloResponse(e.id(), e.getNome(), MarcaResponse.fromEntity(e.getMarca()));
  }
}
