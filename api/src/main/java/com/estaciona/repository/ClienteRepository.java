package com.estaciona.repository;

import com.estaciona.model.Cliente;
import com.estaciona.model.interfaces.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends IRepository<Cliente, Long> {}
