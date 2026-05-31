package com.estaciona.repository;

import com.estaciona.model.Marca;
import com.estaciona.model.interfaces.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends IRepository<Marca, Short> {}
