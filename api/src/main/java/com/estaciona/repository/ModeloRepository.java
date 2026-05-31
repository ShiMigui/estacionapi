package com.estaciona.repository;

import com.estaciona.model.Modelo;
import com.estaciona.model.interfaces.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends IRepository<Modelo, Short> {}
