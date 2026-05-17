package com.estaciona.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "marcas")
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(nullable = false)
  private Short id;

  @ToString.Include
  @Column(nullable = false, unique = true)
  @Size(max = 32)
  private String nome;

  public Marca(String nome) {
    this.rename(nome);
  }

  protected Marca() {}

  public void rename(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }

    this.nome = nome.trim();
  }
}
