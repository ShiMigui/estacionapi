package com.estaciona.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "clientes")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Cliente extends AbstractEntity<Long> {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 150)
  @Column(nullable = false)
  private String nome;

  @NotBlank
  @Size(max = 15)
  @Column(nullable = false)
  private String telefone;

  public void setNome(String nome) {
    if (nome == null || nome.isBlank())
      throw new IllegalArgumentException("Nome não pode ser vazio");

    this.nome = nome.trim();
  }

  public void setTelefone(String telefone) {
    if (telefone == null || !telefone.matches("\\d{10,15}"))
      throw new IllegalArgumentException("Telefone inválido");

    this.telefone = telefone;
  }

  @Override
  protected void internalSetId(Long id) {
    this.id = id;
  }
}
