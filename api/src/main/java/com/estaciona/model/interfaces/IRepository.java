package com.estaciona.model.interfaces;

import com.estaciona.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<Entity extends AbstractEntity<ID>, ID>
    extends JpaRepository<Entity, ID> {}
