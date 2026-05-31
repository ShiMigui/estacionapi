package com.estaciona.service;

import com.estaciona.model.Cliente;
import com.estaciona.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractService<Cliente, Long> {
  public ClienteService(ClienteRepository repo) {
    super(repo, Cliente.class);
  }

  public Cliente update(Cliente newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          String telefone = newData.getTelefone();

          if (nome != null) m.changeNome(nome);
          if (telefone != null) m.changeTelefone(telefone);

          return m;
        });
  }
}
