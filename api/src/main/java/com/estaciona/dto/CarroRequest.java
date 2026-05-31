package com.estaciona.dto;

import com.estaciona.model.Carro;
import com.estaciona.model.Modelo;
import com.estaciona.model.enums.Cor;
import com.estaciona.model.interfaces.IRequest;

public record CarroRequest(String placa, Integer cor_id, Short modelo_id)
    implements IRequest<Carro> {
  @Override
  public Carro entity() {
    Carro carro = new Carro();
    if (placa != null) carro.changePlaca(placa);
    if (cor_id != null) carro.changeCor(Cor.fromId(cor_id));
    if (modelo_id != null) carro.changeModelo(new Modelo(modelo_id));
    return carro;
  }
}
