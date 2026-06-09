package com.techgear.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DispositivoDTO {
    private Integer disId;

    @NotNull(message = "La categoría es obligatoria")
    private Integer cateId;

    private String cateNombre;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 150)
    private String disModelo;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 100)
    private String disMarca;

    private String disEspecificaciones;

    @Size(max = 500)
    private String disImagen;
}
