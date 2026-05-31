package com.estaciona.model.interfaces;

import com.estaciona.model.AbstractEntity;

public interface IRequest<E extends AbstractEntity<?>> {
  E entity();
}
