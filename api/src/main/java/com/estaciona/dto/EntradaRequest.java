package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.Entrada;
import com.estaciona.model.interfaces.IRequest;
import java.time.OffsetDateTime;

public record EntradaRequest(Long cliente_id, Short preco_id, OffsetDateTime saida)
    implements IRequest<Entrada> {
  @Override
  public Entrada entity() {
    Entrada entrada = new Entrada();
    if (saida != null) entrada.changeSaida(saida);
    if (preco_id != null) entrada.changePrecoId(preco_id);
    if (cliente_id != null) entrada.changeCliente(new Cliente(cliente_id));
    return entrada;
  }
}
