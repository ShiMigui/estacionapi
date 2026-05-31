package com.estaciona.dto;

import com.estaciona.model.Carro;
import com.estaciona.model.Modelo;
import com.estaciona.model.enums.Cor;
import com.estaciona.model.interfaces.IRequest;

public record CarroRequest(String placa, Integer cor_id, Short modelo_id)
    implements IRequest<Carro> {
  @Override
  public Carro entity() {
    return new Carro(placa, Cor.fromId(cor_id), new Modelo(modelo_id));
  }
}
