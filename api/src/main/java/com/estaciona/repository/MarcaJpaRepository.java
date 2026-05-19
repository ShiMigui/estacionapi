package com.estaciona.repository;

import com.estaciona.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaJpaRepository extends JpaRepository<Marca, Short> {}
