package com.Ariel_Rom.BloggingPlatform_API.repository;

import com.Ariel_Rom.BloggingPlatform_API.models.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marca esta interfaz como un componente de acceso a datos (para que Spring la detecte e inyecte)
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {
    // Hereda todos los métodos básicos CRUD: findAll(), findById(), save(), deleteById(), etc.

    /*
     * Búsqueda personalizada por término. Filtra posts donde el término aparezca
     * en el título, el contenido o la categoría, sin importar mayúsculas/minúsculas.
     *
     * @param term término de búsqueda (se convierte a minúsculas automáticamente)
     * @return lista de posts que coincidan parcial o totalmente
     */
    @Query("SELECT b FROM BlogPostEntity b WHERE " +
            "LOWER(b.title) LIKE %:term% OR " +
            "LOWER(b.content) LIKE %:term% OR " +
            "LOWER(b.category) LIKE %:term%")
    List<BlogPostEntity> searchByTerm(@Param("term") String term);
}
