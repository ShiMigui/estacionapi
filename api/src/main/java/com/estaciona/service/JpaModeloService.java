package com.estaciona.service;

import com.estaciona.model.Modelo;
import com.estaciona.repository.ModeloRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class JpaModeloService extends JpaService<Modelo, Short> {

  public JpaModeloService(ModeloRepository repo) {
    super(repo);
  }

  public Optional<Modelo> update(Modelo newData) {
    return super.update(
        newData.getId(),
        m -> {
          m.rename(newData.getNome());
          m.changeMarca(newData.getMarca());
          return m;
        });
  }
}
