package com.estaciona.model;

import com.estaciona.exception.domain.ValidationException;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Marca extends AbstractEntity<Short> {
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
  @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
  private List<Modelo> modelos = new ArrayList<>();

  public void changeNome(String nome) {
    if (nome == null || nome.isBlank()) {
      throw new ValidationException("Nome não pode ser vazio");
    }

    this.nome = nome.trim();
  }

  @Override
  protected void internalSetId(Short id) {
    this.id = id;
  }
}
