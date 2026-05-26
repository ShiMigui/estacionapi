package com.estaciona.model;

import com.estaciona.model.converter.CorConverter;
import com.estaciona.model.enums.Cor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "carros")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Carro extends AbstractEntity<Long> {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 7)
  @Column(nullable = false, unique = true)
  private String placa;

  @NotNull
  @Column(nullable = false)
  @Convert(converter = CorConverter.class)
  private Cor cor;

  @NotNull
  @JoinColumn(name = "modelo_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Modelo modelo;

  public void setPlaca(String placa) {
    if (placa == null || !placa.matches("([A-Z]{3}[0-9]{4})|([A-Z]{3}[0-9][A-Z][0-9]{2})"))
      throw new IllegalArgumentException("Placa inválida");
    this.placa = placa;
  }

  public void setCor(Cor cor) {
    if (cor == null) throw new IllegalArgumentException("Cor não pode ser nula");
    this.cor = cor;
  }

  public void setModelo(Modelo modelo) {
    if (modelo == null || modelo.getId() == null)
      throw new IllegalArgumentException("modelo não pode ser nulo");
    this.modelo = modelo;
  }

  @Override
  protected void internalSetId(Long id) {
    this.id = id;
  }
}
