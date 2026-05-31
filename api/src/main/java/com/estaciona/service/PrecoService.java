package com.estaciona.service;

import com.estaciona.model.Preco;
import com.estaciona.repository.PrecoRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class PrecoService extends AbstractService<Preco, Short> {
  public PrecoService(PrecoRepository repo) {
    super(repo, Preco.class);
  }

  public Preco update(Preco newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          String desc = newData.getDescricao();
          BigDecimal preco = newData.getPreco();

          if (preco != null) m.changePreco(preco.doubleValue());
          if (desc != null) m.setDescricao(desc);
          if (nome != null) m.changeNome(nome);

          return m;
        });
  }
}
