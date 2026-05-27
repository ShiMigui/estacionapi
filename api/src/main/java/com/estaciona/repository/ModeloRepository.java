package com.estaciona.repository;

import com.estaciona.model.Modelo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Short> {
  @Query("SELECT DISTINCT m FROM Modelo m LEFT JOIN FETCH m.marca")
  @Override
  List<Modelo> findAll();

  @Query("SELECT DISTINCT m FROM Modelo m LEFT JOIN FETCH m.marca WHERE m.id = :id")
  @Override
  Optional<Modelo> findById(@Param("id") Short id);
}
