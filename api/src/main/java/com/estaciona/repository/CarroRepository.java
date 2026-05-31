package com.estaciona.repository;

import com.estaciona.model.Carro;
import com.estaciona.model.interfaces.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends IRepository<Carro, Long> {}
