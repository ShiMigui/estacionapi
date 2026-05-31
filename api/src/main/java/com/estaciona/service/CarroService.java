package com.estaciona.service;

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
            m.changeModelo(modelos.findById(modelo.getId()));
          if (placa != null) m.changePlaca(placa);
          if (cor != null) m.changeCor(cor);

          return m;
        });
  }

  @Override
  public Carro create(Carro obj) {
    super.create(obj);
    obj.changeModelo(modelos.findWith(obj.getModelo()));
    return obj;
  }
}
