package com.estaciona.service;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.Carro;
import com.estaciona.model.Modelo;
import com.estaciona.model.enums.Cor;
import com.estaciona.model.interfaces.IService;
import com.estaciona.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroService extends AbstractService<Carro, Long> {
  private final IService<Modelo, Short> modelos;

  public CarroService(CarroRepository repo, ModeloService modelos) {
    super(repo, Carro.class);
    this.modelos = modelos;
  }

  @Override
  public Carro update(Carro newData) {
    return super.update(
        newData.getId(),
        m -> {
          String placa = newData.getPlaca();
          Cor cor = newData.getCor();
          Modelo modelo = newData.getModelo();

          if (modelo != null && modelo.getId() != null)
            m.setModelo(modelos.findById(modelo.getId()));
          if (placa != null) m.setPlaca(placa);
          if (cor != null) m.setCor(cor);

          return m;
        });
  }

  @Override
  public Carro create(Carro obj) {
    Modelo modelo = obj.getModelo();
    String placa = obj.getPlaca();
    Cor cor = obj.getCor();

    obj.setPlaca(placa);
    obj.setCor(cor);
    if (modelo == null || modelo.getId() == null)
      throw new ValidationException("Carro deve conter o ID do modelo");
    obj.setModelo(modelos.findById(modelo.getId()));

    return super.create(obj);
  }
}
