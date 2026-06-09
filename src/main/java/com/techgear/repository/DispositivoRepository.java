package com.techgear.repository;

import com.techgear.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
    List<Dispositivo> findByCategoria_CateId(Integer cateId);
    List<Dispositivo> findByDisMarcaIgnoreCase(String marca);
    List<Dispositivo> findByDisModeloContainingIgnoreCase(String modelo);

    @Query("SELECT d FROM Dispositivo d WHERE " +
           "LOWER(d.disModelo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(d.disMarca) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(d.disEspecificaciones) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Dispositivo> buscarPorTermino(@Param("termino") String termino);
}
