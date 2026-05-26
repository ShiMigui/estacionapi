package com.estaciona.model.converter;

import com.estaciona.model.enums.Cor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CorConverter implements AttributeConverter<Cor, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Cor cor) {
    return cor == null ? null : cor.getId();
  }

  @Override
  public Cor convertToEntityAttribute(Integer id) {
    return id == null ? null : Cor.fromId(id);
  }
}
