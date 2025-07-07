package com.Ariel_Rom.BloggingPlatform_API.mappers;

import com.Ariel_Rom.BloggingPlatform_API.models.BlogPostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Hace que MapStruct genere un bean que Spring pueda inyectar automáticamente
public interface BlogPostMapper {

    /*
     * Actualiza un BlogPostEntity existente con los valores de otro,
     * ignorando el campo 'id' y 'createdAt' para evitar sobrescribirlos.
     *
     * @param blogPostEntity El objeto existente que se va a actualizar (target).
     * @param newBlogPostEntity El objeto con los nuevos valores (source).
     */
    @Mapping(target = "id", ignore = true) // No se actualiza el ID
    @Mapping(target = "createdAt", ignore = true) // No se actualiza la fecha de creación
    void updatePost(@MappingTarget BlogPostEntity blogPostEntity, BlogPostEntity newBlogPostEntity);
}
