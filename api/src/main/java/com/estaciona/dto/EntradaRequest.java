package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.Entrada;
import com.estaciona.model.interfaces.IRequest;
import java.time.OffsetDateTime;

public record EntradaRequest(Long cliente_id, Short preco_id, OffsetDateTime saida)
    implements IRequest<Entrada> {
  @Override
  public Entrada entity() {
    return new Entrada(new Cliente(cliente_id), preco_id, saida);
  }
}
