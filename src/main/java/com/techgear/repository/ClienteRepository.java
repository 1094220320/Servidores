package com.techgear.repository;

import com.techgear.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByClieCorreo(String correo);
    List<Cliente> findByClieNombreContainingIgnoreCase(String nombre);
    boolean existsByClieCorreo(String correo);
}
