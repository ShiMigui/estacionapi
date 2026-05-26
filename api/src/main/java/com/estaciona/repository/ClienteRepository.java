package com.estaciona.repository;

import com.estaciona.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  @Override
  @Query(
      "SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.carros ca LEFT JOIN FETCH ca.modelo m"
          + " LEFT JOIN FETCH m.marca")
  List<Cliente> findAll();

  @Override
  @Query(
      "SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.carros ca LEFT JOIN FETCH ca.modelo m"
          + " LEFT JOIN FETCH m.marca WHERE c.id = :id")
  Optional<Cliente> findById(@Param("id") Long id);
}
