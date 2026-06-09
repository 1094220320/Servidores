package com.techgear.repository;

import com.techgear.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByCliente_ClieId(Integer clieId);
    List<Favorito> findByDispositivo_DisId(Integer disId);
    boolean existsByCliente_ClieIdAndDispositivo_DisId(Integer clieId, Integer disId);
    void deleteByCliente_ClieIdAndDispositivo_DisId(Integer clieId, Integer disId);
}
