package com.techgear.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoriaDTO {
    private Integer cateId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String cateNombre;

    private String cateDescripcion;
}
