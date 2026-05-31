package com.estaciona.dto;

import com.estaciona.model.Carro;
import com.estaciona.model.enums.Cor;
import com.estaciona.model.interfaces.IResponse;

public record CarroResponse(Long id, String placa, Cor cor, ModeloResponse modelo)
    implements IResponse<Carro> {

  public static CarroResponse fromEntity(Carro e) {
    return new CarroResponse(
        e.id(), e.getPlaca(), e.getCor(), ModeloResponse.fromEntity(e.getModelo()));
  }
}
