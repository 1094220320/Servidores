package com.techgear.service;

import com.techgear.dto.ClienteDTO;
import com.techgear.exception.RecursoNoEncontradoException;
import com.techgear.model.Cliente;
import com.techgear.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO obtenerPorId(Integer id) {
        return toDTO(buscarOLanzar(id));
    }

    @Transactional
    public ClienteDTO crear(ClienteDTO dto) {
        if (clienteRepository.existsByClieCorreo(dto.getClieCorreo())) {
            throw new IllegalArgumentException("Ya existe un cliente con el correo: " + dto.getClieCorreo());
        }
        Cliente cliente = Cliente.builder()
                .clieNombre(dto.getClieNombre())
                .clieCorreo(dto.getClieCorreo())
                .build();
        return toDTO(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteDTO actualizar(Integer id, ClienteDTO dto) {
        Cliente cliente = buscarOLanzar(id);
        // Validar que el nuevo correo no lo tenga otro cliente
        if (!cliente.getClieCorreo().equals(dto.getClieCorreo())
                && clienteRepository.existsByClieCorreo(dto.getClieCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso: " + dto.getClieCorreo());
        }
        cliente.setClieNombre(dto.getClieNombre());
        cliente.setClieCorreo(dto.getClieCorreo());
        return toDTO(clienteRepository.save(cliente));
    }

    @Transactional
    public void eliminar(Integer id) {
        buscarOLanzar(id);
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findByClieNombreContainingIgnoreCase(nombre)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Cliente buscarOLanzar(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id: " + id));
    }

    private ClienteDTO toDTO(Cliente c) {
        return ClienteDTO.builder()
                .clieId(c.getClieId())
                .clieNombre(c.getClieNombre())
                .clieCorreo(c.getClieCorreo())
                .build();
    }
}
