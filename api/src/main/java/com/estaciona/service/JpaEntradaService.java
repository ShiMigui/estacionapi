package com.estaciona.service;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.Carro;
import com.estaciona.model.Cliente;
import com.estaciona.model.Entrada;
import com.estaciona.model.Preco;
import com.estaciona.model.id.EntradaId;
import com.estaciona.model.interfaces.IService;
import com.estaciona.repository.EntradaRepository;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;

@Service
public class JpaEntradaService extends JpaService<Entrada, EntradaId> {
  private final IService<Carro, Long> carros;
  private final IService<Cliente, Long> clientes;
  private final IService<Preco, Short> precos;

  public JpaEntradaService(
      EntradaRepository repo,
      JpaCarroService carros,
      JpaClienteService clientes,
      JpaPrecoService precos) {
    super(repo, Entrada.class);
    this.carros = carros;
    this.clientes = clientes;
    this.precos = precos;
  }

  @Override
  public Entrada update(Entrada newData) {
    return super.update(
        newData.getId(),
        entrada -> {
          if (entrada.getSaida() != null) throw new IllegalStateException("Entrada já encerrada");

          OffsetDateTime saida = newData.getSaida();
          if (saida != null) entrada.setSaida(saida);

          Cliente cliente = newData.getCliente();
          if (cliente != null && cliente.getId() != null)
            entrada.setCliente(clientes.findById(cliente.getId()));

          Short precoId = newData.getPrecoId();
          if (precoId != null) {
            Preco preco = precos.findById(precoId);

            entrada.setPrecoId(precoId);
            entrada.setPrecoBase(preco.getPreco());
          }

          return entrada;
        });
  }

  @Override
  public Entrada create(Entrada obj) {
    if (obj.getPrecoId() == null) throw new ValidationException("Insira um preço válido");
    if (obj.getEntrada() != null) throw new ValidationException("Carro já tem entrada");
    obj.setPrecoBase(precos.findById(obj.getPrecoId()).getPreco());

    Carro carro = obj.getCarro();
    Cliente cliente = obj.getCliente();
    if (carro == null || carro.getId() == null) throw new ValidationException("Carro invalido");
    if (cliente == null || cliente.getId() == null)
      throw new ValidationException("Cliente invalido");

    obj.setCliente(clientes.findById(cliente.getId()));
    obj.setCarro(carros.findById(carro.getId()));

    return repo.save(obj);
  }
}
