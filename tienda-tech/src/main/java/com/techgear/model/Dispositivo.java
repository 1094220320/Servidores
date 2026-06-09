package com.techgear.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "dispositivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_id")
    private Integer disId;

    // Relación con Categoria (FK: cate_id)
    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id", nullable = false)
    private Categoria categoria;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 150)
    @Column(name = "dis_modelo", nullable = false, length = 150)
    private String disModelo;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 100)
    @Column(name = "dis_marca", nullable = false, length = 100)
    private String disMarca;

    @Column(name = "dis_especificaciones", columnDefinition = "TEXT")
    private String disEspecificaciones;

    @Column(name = "dis_imagen", length = 500)
    private String disImagen;

    // Un dispositivo puede estar en muchos favoritos
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Favorito> favoritos;
}
