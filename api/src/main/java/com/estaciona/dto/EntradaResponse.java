package com.estaciona.dto;

import com.estaciona.model.Entrada;
import com.estaciona.model.interfaces.IResponse;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record EntradaResponse(
    OffsetDateTime entrada,
    CarroResponse carro,
    ClienteResponse cliente,
    BigDecimal preco,
    OffsetDateTime saida,
    Double total)
    implements IResponse<Entrada> {

  public static EntradaResponse fromEntity(Entrada e) {
    OffsetDateTime saida = e.getSaida(), entrada = e.getEntrada();

    return new EntradaResponse(
        entrada,
        CarroResponse.fromEntity(e.getCarro()),
        ClienteResponse.fromEntity(e.getCliente()),
        e.getPrecoBase(),
        saida,
        e.getValorTotal());
  }
}
