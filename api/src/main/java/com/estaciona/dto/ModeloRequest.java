package com.estaciona.dto;

import com.estaciona.model.Marca;
import com.estaciona.model.Modelo;
import com.estaciona.model.interfaces.IRequest;

public record ModeloRequest(String nome, Short marca_id) implements IRequest<Modelo> {
  @Override
  public Modelo entity() {
    Modelo modelo = new Modelo();
    modelo.changeNome(nome);
    modelo.changeMarca(new Marca(marca_id));
    return modelo;
  }
}
