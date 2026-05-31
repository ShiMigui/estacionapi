package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "marcas")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Marca extends AbstractEntity<Short> {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short id;

  @Column(nullable = false, unique = true)
  private String nome;

  public Marca(Short id) {
    this.id = id;
  }

  public void changeNome(String nome) {
    if (nome == null || nome.isBlank()) throw new ValidationException("Nome não pode ser vazio");
    nome = nome.trim();
    if (nome.length() > 32)
      throw new ValidationException("Nome não pode ter mais de 32 caracteres!");

    this.nome = nome;
  }

  @Override
  protected void internalSetId(Short id) {
    this.id = id;
  }
}
