package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
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

  public Modelo(Short id) {
    this.id = id;
  }

  public void changeNome(String nome) {
    if (nome == null || nome.isBlank()) throw new ValidationException("Nome não pode ser vazio");
    nome = nome.trim();
    if (nome.length() > 32)
      throw new ValidationException("Nome não pode ter mais de 32 caracteres");
    this.nome = nome;
  }

  public void changeMarca(Marca marca) {
    if (marca == null || marca.getId() == null) throw new ValidationException("Marca inválida");
    this.marca = marca;
  }

  @Override
  protected void internalSetId(Short id) {
    this.id = id;
  }
}
