package com.techgear.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clie_id")
    private Integer clieId;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 150)
    @Column(name = "clie_nombre", nullable = false, length = 150)
    private String clieNombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 150)
    @Column(name = "clie_correo", nullable = false, unique = true, length = 150)
    private String clieCorreo;

    @Column(name = "clie_password", length = 255)
    private String cliePassword;

    // Un cliente puede tener muchos favoritos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Favorito> favoritos;
}
