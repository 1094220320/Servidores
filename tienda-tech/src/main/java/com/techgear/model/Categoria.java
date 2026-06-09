package com.techgear.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private Integer cateId;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100)
    @Column(name = "cate_nombre", nullable = false, length = 100)
    private String cateNombre;

    @Column(name = "cate_descripcion", columnDefinition = "TEXT")
    private String cateDescripcion;

    // Una categoría tiene muchos dispositivos
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Dispositivo> dispositivos;
}
