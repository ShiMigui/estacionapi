package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.Entrada;
import com.estaciona.model.interfaces.IRequest;

public record EntradaRequest(Long cliente_id, Short preco_id) implements IRequest<Entrada> {
  @Override
  public Entrada entity() {
    Entrada entrada = new Entrada();
    entrada.changePrecoId(preco_id);
    entrada.changeCliente(new Cliente(cliente_id));
    return entrada;
  }
}
