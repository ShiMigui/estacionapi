package com.estaciona.dto;

import java.time.OffsetDateTime;

public record SaidaEntradaRequest(OffsetDateTime saida) {
  @Override
  public OffsetDateTime saida() {
    return saida == null ? OffsetDateTime.now() : saida;
  }
}
