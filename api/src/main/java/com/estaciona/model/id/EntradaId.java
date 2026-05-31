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
  @Column(name = "carro_id")
  private Long carroId;

  @Column(name = "entrada")
  private OffsetDateTime entrada;
}
