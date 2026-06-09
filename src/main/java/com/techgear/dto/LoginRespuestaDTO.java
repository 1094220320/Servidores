package com.techgear.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginRespuestaDTO {
    private Integer clieId;
    private String clieNombre;
    private String clieCorreo;
    private String mensaje;
}
