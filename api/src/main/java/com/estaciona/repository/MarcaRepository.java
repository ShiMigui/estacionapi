package com.estaciona.repository;

import com.estaciona.model.Marca;
import com.estaciona.model.interfaces.IRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends IRepository<Marca, Short> {
  @Query("SELECT DISTINCT m FROM Marca m LEFT JOIN FETCH m.modelos")
  @Override
  List<Marca> findAll();

  @Query("SELECT DISTINCT m FROM Marca m LEFT JOIN FETCH m.modelos WHERE m.id = :id")
  @Override
  Optional<Marca> findById(@Param("id") Short id);
}
