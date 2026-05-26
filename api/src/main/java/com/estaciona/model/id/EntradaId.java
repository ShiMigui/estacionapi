package com.estaciona.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EntradaId implements Serializable {
  @Column(name = "carro_id", nullable = false)
  private Long carro;

  @Column(name = "entrada", nullable = false)
  private OffsetDateTime entrada;
}
