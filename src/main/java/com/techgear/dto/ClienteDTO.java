package com.techgear.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteDTO {
    private Integer clieId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String clieNombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Size(max = 150)
    private String clieCorreo;
}
