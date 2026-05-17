package com.estaciona.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(
    name = "modelos",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "marca_id"})})
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Modelo extends AbstractEntity<Short> {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short id;

  @NotBlank
  @Size(max = 32)
  @Column(nullable = false)
  private String nome;

  @NotNull
  @JsonIgnoreProperties("modelos")
  @JoinColumn(name = "marca_id", nullable = false)
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  private Marca marca;

  public void rename(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    this.nome = nome.trim();
  }

  public void changeMarca(Marca marca) {
    if (marca == null || marca.getId() == null) {
      throw new IllegalArgumentException("Marca inválida");
    }
    this.marca = marca;
  }

  @Override
  protected void internalSetId(Short id) {
    this.id = id;
  }
}
