package com.estaciona.service;

import com.estaciona.exception.domain.EntityNotFoundException;
import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.Carro;
import com.estaciona.model.Cliente;
import com.estaciona.model.Entrada;
import com.estaciona.model.Preco;
import com.estaciona.model.id.EntradaId;
import com.estaciona.model.interfaces.IService;
import com.estaciona.repository.EntradaRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EntradaService extends AbstractService<Entrada, EntradaId> {
  private final IService<Carro, Long> carros;
  private final IService<Preco, Short> precos;
  private final IService<Cliente, Long> clientes;

  public EntradaService(
      EntradaRepository repo, CarroService carros, ClienteService clientes, PrecoService precos) {
    super(repo, Entrada.class);
    this.carros = carros;
    this.clientes = clientes;
    this.precos = precos;
  }

  public Entrada findByIdAndSaidaIsNull(EntradaId id) {
    return ((EntradaRepository) repo)
        .findByIdAndSaidaIsNull(id)
        .orElseThrow(() -> new EntityNotFoundException(entityClass, id));
  }

  public List<Entrada> findBySaidaIsNull() {
    return ((EntradaRepository) repo).findBySaidaIsNull();
  }

  @Override
  public Entrada update(Entrada newData) {
    return super.update(
        newData.getId(),
        entrada -> {
          if (entrada.getSaida() != null) throw new IllegalStateException("Entrada já encerrada");

          OffsetDateTime saida = newData.getSaida();
          if (saida != null) entrada.changeSaida(saida);

          Cliente cliente = newData.getCliente();
          if (cliente != null && cliente.getId() != null)
            entrada.changeCliente(clientes.findWith(cliente));

          Short precoId = newData.getPrecoId();
          if (precoId != null) {
            Preco preco = precos.findById(precoId);
            entrada.changePrecoId(precoId);
            entrada.changePrecoBase(preco.getPreco());
          }

          entrada.calcValorTotal();

          return entrada;
        });
  }

  @Override
  public Entrada create(Entrada obj) {
    Short precoId = obj.getPrecoId();
    if (precoId == null) throw new ValidationException("Insira um preço válido");
    obj.changePrecoBase(precos.findById(precoId).getPreco());

    repo.save(obj);

    return findWith(obj);
  }
}
