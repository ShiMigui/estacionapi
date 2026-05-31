package com.estaciona.dto;

import com.estaciona.model.Cliente;
import com.estaciona.model.interfaces.IRequest;

public record ClienteRequest(String nome, String telefone) implements IRequest<Cliente> {
  @Override
  public Cliente entity() {
    Cliente cliente = new Cliente();
    if (nome != null) cliente.changeNome(nome);
    if (telefone != null) cliente.changeTelefone(telefone);
    return cliente;
  }
}
