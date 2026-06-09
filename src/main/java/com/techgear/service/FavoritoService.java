package com.techgear.service;

import com.techgear.dto.FavoritoDTO;
import com.techgear.exception.RecursoNoEncontradoException;
import com.techgear.model.Cliente;
import com.techgear.model.Dispositivo;
import com.techgear.model.Favorito;
import com.techgear.repository.ClienteRepository;
import com.techgear.repository.DispositivoRepository;
import com.techgear.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final ClienteRepository clienteRepository;
    private final DispositivoRepository dispositivoRepository;

    @Transactional(readOnly = true)
    public List<FavoritoDTO> obtenerTodos() {
        return favoritoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FavoritoDTO> obtenerPorCliente(Integer clieId) {
        return favoritoRepository.findByCliente_ClieId(clieId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public FavoritoDTO agregar(FavoritoDTO dto) {
        if (favoritoRepository.existsByCliente_ClieIdAndDispositivo_DisId(dto.getClieId(), dto.getDisId())) {
            throw new IllegalArgumentException("Este dispositivo ya está en favoritos del cliente");
        }
        Cliente cliente = clienteRepository.findById(dto.getClieId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado: " + dto.getClieId()));
        Dispositivo dispositivo = dispositivoRepository.findById(dto.getDisId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Dispositivo no encontrado: " + dto.getDisId()));

        Favorito favorito = Favorito.builder()
                .cliente(cliente)
                .dispositivo(dispositivo)
                .build();
        return toDTO(favoritoRepository.save(favorito));
    }

    @Transactional
    public void eliminar(Integer favId) {
        if (!favoritoRepository.existsById(favId)) {
            throw new RecursoNoEncontradoException("Favorito no encontrado con id: " + favId);
        }
        favoritoRepository.deleteById(favId);
    }

    @Transactional
    public void eliminarPorClienteYDispositivo(Integer clieId, Integer disId) {
        favoritoRepository.deleteByCliente_ClieIdAndDispositivo_DisId(clieId, disId);
    }

    private FavoritoDTO toDTO(Favorito f) {
        return FavoritoDTO.builder()
                .favId(f.getFavId())
                .clieId(f.getCliente().getClieId())
                .clieNombre(f.getCliente().getClieNombre())
                .disId(f.getDispositivo().getDisId())
                .disModelo(f.getDispositivo().getDisModelo())
                .disMarca(f.getDispositivo().getDisMarca())
                .build();
    }
}
