package com.estaciona.service;

import com.estaciona.model.Marca;
import com.estaciona.model.Modelo;
import com.estaciona.repository.ModeloRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaModeloService extends JpaService<Modelo, Short> {
  private final JpaService<Marca, Short> marcaRepo;

  public JpaModeloService(ModeloRepository repo, JpaMarcaService marcaRepo) {
    super(repo, Modelo.class);
    this.marcaRepo = marcaRepo;
  }

  public Modelo update(Modelo newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          Marca marca = newData.getMarca();

          if (nome != null) m.rename(nome);

          if (marca != null && marca.getId() != null)
            m.changeMarca(marcaRepo.findById(marca.getId()));

          return m;
        });
  }
}
