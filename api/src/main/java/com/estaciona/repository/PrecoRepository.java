package com.estaciona.repository;

import com.estaciona.model.Preco;
import com.estaciona.model.interfaces.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends IRepository<Preco, Short> {}
