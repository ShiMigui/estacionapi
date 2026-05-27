package com.estaciona.service;

import com.estaciona.model.Marca;
import com.estaciona.repository.MarcaRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaMarcaService extends JpaService<Marca, Short> {
  public JpaMarcaService(MarcaRepository repo) {
    super(repo, Marca.class);
  }

  @Override
  public Marca update(Marca newData) {
    return super.update(
        newData.getId(),
        m -> {
          m.rename(newData.getNome());
          return m;
        });
  }

  @Override
  public Marca create(Marca obj) {
    obj.rename(obj.getNome());
    return super.create(obj);
  }
}
