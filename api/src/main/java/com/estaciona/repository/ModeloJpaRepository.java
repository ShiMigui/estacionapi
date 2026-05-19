package com.estaciona.repository;

import com.estaciona.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloJpaRepository extends JpaRepository<Modelo, Short> {}
