package com.techgear.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_id")
    private Integer favId;

    // Relación con Cliente (FK: clie_id)
    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clie_id", nullable = false)
    private Cliente cliente;

    // Relación con Dispositivo (FK: dis_id)
    @NotNull(message = "El dispositivo es obligatorio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dis_id", nullable = false)
    private Dispositivo dispositivo;
}
