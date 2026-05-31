package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.interfaces.IResponse;

public record ClienteResponse(Long id, String nome, String telefone) implements IResponse<Cliente> {
  public static ClienteResponse fromEntity(Cliente e) {
    return new ClienteResponse(e.id(), e.getNome(), e.getTelefone());
  }
}
