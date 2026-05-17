package com.estaciona.service;

import com.estaciona.model.Marca;
import com.estaciona.repository.MarcaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class JpaMarcaService extends JpaService<Marca, Short> {
  public JpaMarcaService(MarcaRepository repo) {
    super(repo);
  }

  @Override
  public Optional<Marca> update(Marca newData) {
    return super.update(
        newData.getId(),
        m -> {
          m.rename(newData.getNome());
          return m;
        });
  }
}
