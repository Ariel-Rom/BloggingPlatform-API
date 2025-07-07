package com.Ariel_Rom.BloggingPlatform_API.services;

import com.Ariel_Rom.BloggingPlatform_API.mappers.BlogPostMapper;
import com.Ariel_Rom.BloggingPlatform_API.models.BlogPostEntity;
import com.Ariel_Rom.BloggingPlatform_API.repository.BlogPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca esta clase como un servicio de Spring (componente de lógica de negocio)
@RequiredArgsConstructor // Lombok: genera un constructor con todos los campos 'final'
public class BlogPostService {

    private final BlogPostRepository blogPostRepository; // Acceso a base de datos
    private final BlogPostMapper blogPostMapper;         // Mapper para actualizar objetos de forma limpia

    /*
     * Crea un nuevo post y lo guarda en la base de datos.
     */
    public BlogPostEntity createPost(BlogPostEntity newBlogPost) {
        return blogPostRepository.save(newBlogPost);
    }

    /*
     * Busca un post por su ID. Lanza una excepción si no se encuentra.
     */
    public BlogPostEntity getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found!"));
    }

    /*
     * Busca posts por un término que puede estar en el título, contenido o categoría.
     * El término se pasa a minúsculas para asegurar coincidencias insensibles a mayúsculas.
     */
    public List<BlogPostEntity> searchPosts(String term) {
        return blogPostRepository.searchByTerm(term.toLowerCase());
    }

    /*
     * Devuelve todos los posts almacenados en la base de datos.
     */
    public List<BlogPostEntity> getAllPosts() {
        return blogPostRepository.findAll();
    }

    /*
     * Actualiza un post existente. Solo modifica campos permitidos gracias al mapper.
     * Ignora ID y fecha de creación.
     */
    public BlogPostEntity updatePost(BlogPostEntity newPost, Long id) {
        // Busca el post a actualizar o lanza excepción si no existe
        BlogPostEntity blogPostEntity = blogPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found!"));

        // Usa MapStruct para copiar los nuevos valores al objeto existente
        blogPostMapper.updatePost(blogPostEntity, newPost);

        // Guarda los cambios en la base de datos
        blogPostRepository.save(blogPostEntity);

        return blogPostEntity;
    }

    /*
     * Elimina un post por su ID. Lanza excepción si no existe.
     */
    public void deletePost(Long id) {

        blogPostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found!"));

        blogPostRepository.deleteById(id);
    }
}
