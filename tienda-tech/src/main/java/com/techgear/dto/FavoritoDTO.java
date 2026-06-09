package com.techgear.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FavoritoDTO {
    private Integer favId;

    @NotNull(message = "El cliente es obligatorio")
    private Integer clieId;

    private String clieNombre;

    @NotNull(message = "El dispositivo es obligatorio")
    private Integer disId;

    private String disModelo;
    private String disMarca;
}
