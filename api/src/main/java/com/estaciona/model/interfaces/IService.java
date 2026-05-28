package com.estaciona.model.interfaces;

import com.estaciona.model.AbstractEntity;
import java.util.List;

public interface IService<Entity extends AbstractEntity<ID>, ID> {
  public Entity findById(ID id);

  public Entity create(Entity obj);

  public List<Entity> all();

  public Entity update(Entity newData);

  public boolean delete(ID id);
}
