package com.techgear.repository;

import com.techgear.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByCateNombreContainingIgnoreCase(String nombre);
    boolean existsByCateNombre(String nombre);
}
