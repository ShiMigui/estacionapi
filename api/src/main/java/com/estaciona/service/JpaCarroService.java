package com.estaciona.service;

import com.estaciona.model.Carro;
import com.estaciona.model.enums.Cor;
import com.estaciona.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaCarroService extends JpaService<Carro, Long> {
  public JpaCarroService(CarroRepository repo) {
    super(repo, Carro.class);
  }

  public Carro update(Carro newData) {
    return super.update(
        newData.getId(),
        m -> {
          String placa = newData.getPlaca();
          Cor cor = newData.getCor();

          if (placa != null) m.setPlaca(placa);
          if (cor != null) m.setCor(cor);

          return m;
        });
  }
}
