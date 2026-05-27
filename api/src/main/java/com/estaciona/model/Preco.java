package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "precos")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Preco extends AbstractEntity<Short> {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short id;

  @NotBlank
  @Size(max = 100)
  @Column(nullable = false)
  private String nome;

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal preco;

  @Column(columnDefinition = "TEXT")
  private String descricao;

  public void setPreco(BigDecimal preco) {
    if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0)
      throw new ValidationException("Preço não pode ser negativo");
    this.preco = preco;
  }

  public void setNome(String nome) {
    if (nome.length() < 10)
      throw new ValidationException("Nome de preco deve conter no mínimo 10 caracteres");
    this.nome = nome;
  }

  @Override
  protected void internalSetId(Short id) {
    this.id = id;
  }
}
