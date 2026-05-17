package com.estaciona.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "marcas")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Marca {
  @Id
  @ToString.Include
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short id;

  @NotBlank
  @Size(max = 32)
  @Column(nullable = false, unique = true)
  private String nome;

  @JsonIgnoreProperties("marca")
  @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
  private List<Modelo> modelos = new ArrayList<>();

  public Marca(Short id, String nome) {
    this.id = id;
    this.rename(nome);
  }

  public void rename(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser vazio");
    }

    this.nome = nome.trim();
  }

  public void setId(Short id) {
    if (this.id != null) {
      if (this.id == id) return;
      throw new IllegalArgumentException("ID já foi definido e não pode ser alterado");
    }
    this.id = id;
  }
}
