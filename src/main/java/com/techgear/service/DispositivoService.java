package com.techgear.service;

import com.techgear.dto.DispositivoDTO;
import com.techgear.exception.RecursoNoEncontradoException;
import com.techgear.model.Categoria;
import com.techgear.model.Dispositivo;
import com.techgear.repository.CategoriaRepository;
import com.techgear.repository.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<DispositivoDTO> obtenerTodos() {
        return dispositivoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DispositivoDTO obtenerPorId(Integer id) {
        return toDTO(buscarOLanzar(id));
    }

    @Transactional
    public DispositivoDTO crear(DispositivoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCateId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con id: " + dto.getCateId()));
        Dispositivo dispositivo = Dispositivo.builder()
                .categoria(categoria)
                .disModelo(dto.getDisModelo())
                .disMarca(dto.getDisMarca())
                .disEspecificaciones(dto.getDisEspecificaciones())
                .build();
        return toDTO(dispositivoRepository.save(dispositivo));
    }

    @Transactional
    public DispositivoDTO actualizar(Integer id, DispositivoDTO dto) {
        Dispositivo dispositivo = buscarOLanzar(id);
        Categoria categoria = categoriaRepository.findById(dto.getCateId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con id: " + dto.getCateId()));
        dispositivo.setCategoria(categoria);
        dispositivo.setDisModelo(dto.getDisModelo());
        dispositivo.setDisMarca(dto.getDisMarca());
        dispositivo.setDisEspecificaciones(dto.getDisEspecificaciones());
        return toDTO(dispositivoRepository.save(dispositivo));
    }

    @Transactional
    public void eliminar(Integer id) {
        buscarOLanzar(id);
        dispositivoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DispositivoDTO> buscarPorTermino(String termino) {
        return dispositivoRepository.buscarPorTermino(termino)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DispositivoDTO> obtenerPorCategoria(Integer cateId) {
        return dispositivoRepository.findByCategoria_CateId(cateId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DispositivoDTO> obtenerPorMarca(String marca) {
        return dispositivoRepository.findByDisMarcaIgnoreCase(marca)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Dispositivo buscarOLanzar(Integer id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dispositivo no encontrado con id: " + id));
    }

    private DispositivoDTO toDTO(Dispositivo d) {
        return DispositivoDTO.builder()
                .disId(d.getDisId())
                .cateId(d.getCategoria().getCateId())
                .cateNombre(d.getCategoria().getCateNombre())
                .disModelo(d.getDisModelo())
                .disMarca(d.getDisMarca())
                .disEspecificaciones(d.getDisEspecificaciones())
                .disImagen(d.getDisImagen())
                .build();
    }
}
