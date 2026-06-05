package com.estaciona.repository;

import com.estaciona.model.Entrada;
import com.estaciona.model.id.EntradaId;
import com.estaciona.model.interfaces.IRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends IRepository<Entrada, EntradaId> {
  List<Entrada> findBySaidaIsNull();

  Optional<Entrada> findByIdAndSaidaIsNull(EntradaId id);

  Optional<Entrada> findByCarroIdAndSaidaIsNull(Long carroId);
}
