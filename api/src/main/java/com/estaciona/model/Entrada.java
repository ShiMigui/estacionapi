package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
import com.estaciona.model.id.EntradaId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entradas")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada extends AbstractEntity<EntradaId> {
  @EmbeddedId private EntradaId id;

  @MapsId("carro")
  @JsonIgnoreProperties("entradas")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "carro_id", nullable = false, updatable = false)
  private Carro carro;

  @JsonIgnoreProperties("entradas")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id", nullable = false, updatable = false)
  private Cliente cliente;

  @Column(name = "preco_base", precision = 10, scale = 2, nullable = false)
  private BigDecimal precoBase;

  @Column(name = "preco_id")
  private Short precoId;

  @Column(name = "saida")
  private OffsetDateTime saida;

  @Override
  protected void internalSetId(EntradaId id) {
    this.id = id;
  }

  public void setCarro(Carro carro) {
    if (carro == null) throw new IllegalArgumentException("Carro cannot be null");
    this.carro = carro;
  }

  public void setCliente(Cliente cliente) {
    if (cliente == null) throw new IllegalArgumentException("Cliente cannot be null");
    this.cliente = cliente;
  }

  public void setPrecoBase(BigDecimal precoBase) {
    if (precoBase == null || precoBase.compareTo(BigDecimal.ZERO) < 0)
      throw new IllegalArgumentException("Preço base must be a non-negative number");
    this.precoBase = precoBase;
  }

  public void setPrecoId(Short precoId) {
    if (precoId == null || precoId < 0)
      throw new IllegalArgumentException("Preço ID must be a non-negative number");
    this.precoId = precoId;
  }

  public void setSaida(OffsetDateTime saida) {
    if (saida != null && saida.isBefore(getEntrada()))
      throw new IllegalArgumentException("Saída date cannot be in the past");
    this.saida = saida;
  }

  public void changeCarro(Carro carro) {
    if (carro == null || carro.getId() == null) throw new ValidationException("Carro invalido");
    this.carro = carro;
    this.id.setCarro(carro.getId());
  }

  public OffsetDateTime getEntrada() {
    return id.getEntrada();
  }
}
