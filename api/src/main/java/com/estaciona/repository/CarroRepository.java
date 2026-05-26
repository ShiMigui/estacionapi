package com.estaciona.repository;

import com.estaciona.model.Carro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
  @Override
  @Query(
      "SELECT DISTINCT c FROM Carro c JOIN FETCH c.modelo m JOIN FETCH m.marca LEFT JOIN FETCH"
          + " c.responsaveis")
  List<Carro> findAll();

  @Override
  @Query(
      "SELECT DISTINCT c FROM Carro c JOIN FETCH c.modelo m JOIN FETCH m.marca LEFT JOIN FETCH"
          + " c.responsaveis WHERE c.id = :id")
  Optional<Carro> findById(@Param("id") Long id);
}
