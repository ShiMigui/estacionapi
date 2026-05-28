package com.estaciona.model.interfaces;

import com.estaciona.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository<Entity extends AbstractEntity<ID>, ID>
    extends JpaRepository<Entity, ID> {}
