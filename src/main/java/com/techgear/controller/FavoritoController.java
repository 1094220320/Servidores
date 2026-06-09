package com.techgear.controller;

import com.techgear.dto.FavoritoDTO;
import com.techgear.service.FavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favoritos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    // GET /api/v1/favoritos
    @GetMapping
    public ResponseEntity<List<FavoritoDTO>> obtenerTodos() {
        return ResponseEntity.ok(favoritoService.obtenerTodos());
    }

    // GET /api/v1/favoritos/cliente/3
    @GetMapping("/cliente/{clieId}")
    public ResponseEntity<List<FavoritoDTO>> porCliente(@PathVariable Integer clieId) {
        return ResponseEntity.ok(favoritoService.obtenerPorCliente(clieId));
    }

    // POST /api/v1/favoritos
    @PostMapping
    public ResponseEntity<FavoritoDTO> agregar(@Valid @RequestBody FavoritoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.agregar(dto));
    }

    // DELETE /api/v1/favoritos/{favId}
    @DeleteMapping("/{favId}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer favId) {
        favoritoService.eliminar(favId);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/v1/favoritos/cliente/3/dispositivo/7
    @DeleteMapping("/cliente/{clieId}/dispositivo/{disId}")
    public ResponseEntity<Void> eliminarPorClienteYDispositivo(
            @PathVariable Integer clieId,
            @PathVariable Integer disId) {
        favoritoService.eliminarPorClienteYDispositivo(clieId, disId);
        return ResponseEntity.noContent().build();
    }
}
