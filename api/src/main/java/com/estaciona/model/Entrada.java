package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.id.EntradaId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "entradas")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Entrada extends AbstractEntity<EntradaId> {
  @EmbeddedId private EntradaId id;

  @MapsId("carroId")
  @JsonIgnoreProperties("entradas")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "carro_id", nullable = false, updatable = false)
  private Carro carro;

  @JsonIgnoreProperties("entradas")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "cliente_id", nullable = false, updatable = false)
  private Cliente cliente;

  @Column(name = "preco_base", precision = 10, scale = 2, nullable = false)
  private BigDecimal precoBase;

  @Column(name = "preco_id")
  private Short precoId;

  @Column(name = "saida")
  private OffsetDateTime saida;

  @Column(name = "valor_total")
  private Double valorTotal;

  public void changeCliente(Cliente cliente) {
    if (cliente == null || cliente.getId() == null)
      throw new ValidationException("Cliente cannot be null");
    this.cliente = cliente;
  }

  public void changePrecoBase(BigDecimal precoBase) {
    if (precoBase == null || precoBase.compareTo(BigDecimal.ZERO) < 0)
      throw new ValidationException("Preço base não pode ser negativo");
    this.precoBase = precoBase;
  }

  public void changePrecoId(Short precoId) {
    if (precoId == null || precoId < 0)
      throw new ValidationException("Preço ID deve ser um número inteiro positivo");
    this.precoId = precoId;
  }

  public void changeSaida(OffsetDateTime saida) {
    if (getEntrada() != null && saida.isBefore(getEntrada()))
      throw new ValidationException("Saída não pode ser anterior a entrada");
    this.saida = saida;
  }

  public void calcValorTotal() {
    if (saida != null && precoBase != null && valorTotal == null) {
      this.valorTotal =
          precoBase.doubleValue()
              * Math.ceil((double) Duration.between(getEntrada(), saida).toMinutes() / 60);
    }
  }

  public OffsetDateTime getEntrada() {
    return id.getEntrada();
  }

  public double precoBase() {
    if (precoBase == null) throw new ValidationException("Preço base não definido na entrada");
    return precoBase.doubleValue();
  }

  @Override
  public void changeId(EntradaId id) {
    Long carroId = id.getCarroId();
    if (carroId == null) throw new ValidationException("Entrada ID deve conter o carro que entrou");
    this.carro = new Carro(carroId);
    super.changeId(id);
  }

  public void changeCarro(Carro carro) {
    this.carro = carro;
    this.id.setCarroId(carro.id());
  }

  @Override
  protected void internalSetId(EntradaId id) {
    this.id = id;
  }
}
