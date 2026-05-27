package com.estaciona.service;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.Marca;
import com.estaciona.model.Modelo;
import com.estaciona.repository.ModeloRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaModeloService extends JpaService<Modelo, Short> {
  private final JpaService<Marca, Short> marcaService;

  public JpaModeloService(ModeloRepository repo, JpaMarcaService marcaService) {
    super(repo, Modelo.class);
    this.marcaService = marcaService;
  }

  public Modelo update(Modelo newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          Marca marca = newData.getMarca();

          if (nome != null) m.rename(nome);

          if (marca != null && marca.getId() != null)
            m.changeMarca(marcaService.findById(marca.getId()));

          return m;
        });
  }

  @Override
  public Modelo create(Modelo obj) {
    obj.rename(obj.getNome());
    Marca marca = obj.getMarca();
    if (marca == null || marca.getId() == null)
      throw new ValidationException("Modelo deve conter o ID da marca");
    obj.changeMarca(marcaService.findById(marca.getId()));
    return repo.save(obj);
  }
}
