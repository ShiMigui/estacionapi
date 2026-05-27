package com.estaciona.service;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.Preco;
import com.estaciona.repository.PrecoRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class JpaPrecoService extends JpaService<Preco, Short> {
  public JpaPrecoService(PrecoRepository repo) {
    super(repo, Preco.class);
  }

  public Preco update(Preco newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          String desc = newData.getDescricao();
          BigDecimal preco = newData.getPreco();

          if (nome != null) m.setPreco(preco);
          if (desc != null) m.setDescricao(desc);
          if (nome != null) m.setNome(nome);

          return m;
        });
  }

  @Override
  public Preco create(Preco obj) {
    BigDecimal preco = obj.getPreco();

    obj.setNome(obj.getNome());
    if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0)
      throw new ValidationException("O valor do preco não deve ser negativo!");

    return repo.save(obj);
  }
}
