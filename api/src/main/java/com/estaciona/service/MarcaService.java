package com.estaciona.service;

import com.estaciona.model.Marca;
import com.estaciona.repository.MarcaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
  private final JpaRepository<Marca, Short> repo;

  public MarcaService(MarcaRepository marcas) {
    this.repo = marcas;
  }

  public Optional<Marca> findById(Short id) {
    return repo.findById(id);
  }

  public Marca save(Marca marca) {
    return repo.save(marca);
  }

  public List<Marca> getAll() {
    return repo.findAll();
  }

  public Optional<Marca> update(Short id, String nome) {
    return repo.findById(id)
        .map(
            m -> {
              m.rename(nome);
              return repo.save(m);
            });
  }

  public boolean delete(Short id) {
    if (!repo.existsById(id)) {
      return false;
    }

    repo.deleteById(id);
    return true;
  }
}
