package com.techgear.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginDTO {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String clieCorreo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String cliePassword;
}
