package com.techgear.service;

import com.techgear.dto.CategoriaDTO;
import com.techgear.exception.RecursoNoEncontradoException;
import com.techgear.model.Categoria;
import com.techgear.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> obtenerTodas() {
        return categoriaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO obtenerPorId(Integer id) {
        return toDTO(buscarOLanzar(id));
    }

    @Transactional
    public CategoriaDTO crear(CategoriaDTO dto) {
        log.info("Creando categoría: {}", dto.getCateNombre());
        Categoria categoria = Categoria.builder()
                .cateNombre(dto.getCateNombre())
                .cateDescripcion(dto.getCateDescripcion())
                .build();
        return toDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaDTO actualizar(Integer id, CategoriaDTO dto) {
        Categoria categoria = buscarOLanzar(id);
        categoria.setCateNombre(dto.getCateNombre());
        categoria.setCateDescripcion(dto.getCateDescripcion());
        return toDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public void eliminar(Integer id) {
        buscarOLanzar(id);
        categoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> buscarPorNombre(String nombre) {
        return categoriaRepository.findByCateNombreContainingIgnoreCase(nombre)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Categoria buscarOLanzar(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con id: " + id));
    }

    private CategoriaDTO toDTO(Categoria c) {
        return CategoriaDTO.builder()
                .cateId(c.getCateId())
                .cateNombre(c.getCateNombre())
                .cateDescripcion(c.getCateDescripcion())
                .build();
    }
}
