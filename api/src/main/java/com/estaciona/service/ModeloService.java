package com.estaciona.service;

import com.estaciona.model.Marca;
import com.estaciona.model.Modelo;
import com.estaciona.model.interfaces.IService;
import com.estaciona.repository.ModeloRepository;
import org.springframework.stereotype.Service;

@Service
public class ModeloService extends AbstractService<Modelo, Short> {
  private final IService<Marca, Short> marcaService;

  public ModeloService(ModeloRepository repo, MarcaService marcaService) {
    super(repo, Modelo.class);
    this.marcaService = marcaService;
  }

  public Modelo update(Modelo newData) {
    return super.update(
        newData.getId(),
        m -> {
          String nome = newData.getNome();
          Marca marca = newData.getMarca();

          if (nome != null) m.changeNome(nome);

          if (marca != null && marca.getId() != null)
            m.changeMarca(marcaService.findById(marca.getId()));

          return m;
        });
  }

  @Override
  public Modelo create(Modelo obj) {
    super.create(obj);
    obj.changeMarca(marcaService.findWith(obj.getMarca()));
    return obj;
  }
}
