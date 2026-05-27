package com.estaciona.repository;

import com.estaciona.model.Entrada;
import com.estaciona.model.id.EntradaId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, EntradaId> {
  @Override
  @Query(
      """
      SELECT e
      FROM Entrada e
      JOIN FETCH e.carro c
      JOIN FETCH c.modelo m
      JOIN FETCH m.marca
      JOIN FETCH e.cliente
      """)
  List<Entrada> findAll();

  @Override
  @Query(
      """
      SELECT e
      FROM Entrada e
      JOIN FETCH e.carro c
      JOIN FETCH c.modelo m
      JOIN FETCH m.marca
      JOIN FETCH e.cliente
      WHERE e.id = :id
      """)
  Optional<Entrada> findById(@Param("id") EntradaId id);
}
