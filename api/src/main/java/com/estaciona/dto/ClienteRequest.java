package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.interfaces.IRequest;

public record ClienteRequest(String nome, String telefone) implements IRequest<Cliente> {
  @Override
  public Cliente entity() {
    return new Cliente(nome, telefone);
  }
}
