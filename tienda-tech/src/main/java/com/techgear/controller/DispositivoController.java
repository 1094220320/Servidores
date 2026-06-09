package com.techgear.controller;

import com.techgear.dto.DispositivoDTO;
import com.techgear.service.DispositivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dispositivos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    @GetMapping
    public ResponseEntity<List<DispositivoDTO>> obtenerTodos() {
        return ResponseEntity.ok(dispositivoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(dispositivoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<DispositivoDTO> crear(@Valid @RequestBody DispositivoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dispositivoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoDTO> actualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody DispositivoDTO dto) {
        return ResponseEntity.ok(dispositivoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        dispositivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/v1/dispositivos/buscar?termino=laptop
    @GetMapping("/buscar")
    public ResponseEntity<List<DispositivoDTO>> buscar(@RequestParam String termino) {
        return ResponseEntity.ok(dispositivoService.buscarPorTermino(termino));
    }

    // GET /api/v1/dispositivos/categoria/2
    @GetMapping("/categoria/{cateId}")
    public ResponseEntity<List<DispositivoDTO>> porCategoria(@PathVariable Integer cateId) {
        return ResponseEntity.ok(dispositivoService.obtenerPorCategoria(cateId));
    }

    // GET /api/v1/dispositivos/marca/Dell
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<DispositivoDTO>> porMarca(@PathVariable String marca) {
        return ResponseEntity.ok(dispositivoService.obtenerPorMarca(marca));
    }
}
