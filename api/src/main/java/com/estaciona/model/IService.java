package com.estaciona.model;

import java.util.List;

public interface IService<Entity extends AbstractEntity<ID>, ID> {
  public Entity findById(ID id);

  public Entity create(Entity obj);

  public List<Entity> getAll();

  public Entity update(Entity newData);

  public boolean delete(ID id);
}
