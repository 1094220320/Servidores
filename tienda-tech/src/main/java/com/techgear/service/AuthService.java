package com.techgear.service;

import com.techgear.dto.LoginDTO;
import com.techgear.dto.LoginRespuestaDTO;
import com.techgear.dto.RegistroDTO;
import com.techgear.exception.RecursoNoEncontradoException;
import com.techgear.model.Cliente;
import com.techgear.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public LoginRespuestaDTO registrar(RegistroDTO dto) {
        if (clienteRepository.existsByClieCorreo(dto.getClieCorreo())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese correo");
        }
        Cliente cliente = Cliente.builder()
                .clieNombre(dto.getClieNombre())
                .clieCorreo(dto.getClieCorreo())
                .cliePassword(passwordEncoder.encode(dto.getCliePassword()))
                .build();
        Cliente guardado = clienteRepository.save(cliente);
        log.info("Nuevo cliente registrado: {}", guardado.getClieCorreo());
        return LoginRespuestaDTO.builder()
                .clieId(guardado.getClieId())
                .clieNombre(guardado.getClieNombre())
                .clieCorreo(guardado.getClieCorreo())
                .mensaje("Registro exitoso")
                .build();
    }

    @Transactional(readOnly = true)
    public LoginRespuestaDTO login(LoginDTO dto) {
        Cliente cliente = clienteRepository.findByClieCorreo(dto.getClieCorreo())
                .orElseThrow(() -> new RecursoNoEncontradoException("Correo o contraseña incorrectos"));

        if (cliente.getCliePassword() == null ||
                !passwordEncoder.matches(dto.getCliePassword(), cliente.getCliePassword())) {
            throw new IllegalArgumentException("Correo o contraseña incorrectos");
        }

        log.info("Login exitoso: {}", cliente.getClieCorreo());
        return LoginRespuestaDTO.builder()
                .clieId(cliente.getClieId())
                .clieNombre(cliente.getClieNombre())
                .clieCorreo(cliente.getClieCorreo())
                .mensaje("Login exitoso")
                .build();
    }
}
