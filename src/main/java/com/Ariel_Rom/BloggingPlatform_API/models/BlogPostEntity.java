package com.Ariel_Rom.BloggingPlatform_API.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity // Marca esta clase como una entidad de JPA (tabla en la base de datos)
@Data // Anotación de Lombok que genera getters, setters, toString, equals y hashCode automáticamente
@Table(name = "blog_posts") // Define el nombre de la tabla en la base de datos
public class BlogPostEntity {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental (lo maneja la DB)
    private Long id;

    @Column(nullable = false, length = 150) // No puede ser null, máx 150 caracteres
    @NotBlank // Validación: no puede estar vacío ni ser solo espacios
    private String title;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String content;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String category;

    @ElementCollection // Indica que esta lista se guarda en una tabla separada
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id")) // Configura la tabla de tags y su relación con el post
    @Column(name = "tags") // Nombre de la columna donde se guardan los tags individuales
    private List<String> tags;

    @Column(name = "creation_date") // Fecha de creación
    private LocalDateTime createdAt;

    @Column(name = "update_date") // Fecha de última modificación
    private LocalDateTime updatedAt;

    /*
     * Metodo que se ejecuta automáticamente antes de insertar el registro en la base de datos.
     * Asigna la fecha y hora actual al campo createdAt.
     */
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /*
     * Metodo que se ejecuta automáticamente antes de actualizar el registro.
     * Asigna la fecha y hora actual al campo updatedAt.
     */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
