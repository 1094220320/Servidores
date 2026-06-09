package com.techgear.controller;

import com.techgear.dto.LoginDTO;
import com.techgear.dto.LoginRespuestaDTO;
import com.techgear.dto.RegistroDTO;
import com.techgear.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    // POST /api/v1/auth/registro
    @PostMapping("/registro")
    public ResponseEntity<LoginRespuestaDTO> registro(@Valid @RequestBody RegistroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(dto));
    }

    // POST /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<LoginRespuestaDTO> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
