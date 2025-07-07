package com.Ariel_Rom.BloggingPlatform_API.controllers;

import com.Ariel_Rom.BloggingPlatform_API.models.BlogPostEntity;
import com.Ariel_Rom.BloggingPlatform_API.services.BlogPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/app/v1") // Prefijo base para todas las rutas de este controlador
@RequiredArgsConstructor // Genera el constructor con los atributos marcados como 'final'
public class BlogPostController {

    // Servicio que maneja la lógica de negocio relacionada a los posts
    private final BlogPostService blogPostService;

    /*
     * Endpoint para crear un nuevo post.
     * Recibe un objeto JSON válido, lo valida y lo guarda.
     */
    @PostMapping("/post")
    public ResponseEntity<BlogPostEntity> createPost(@RequestBody @Valid BlogPostEntity newPost) {
        return ResponseEntity.ok(blogPostService.createPost(newPost));
    }

    /*
     * Endpoint para obtener un post específico por su ID.
     */
    @GetMapping("{id}")
    public ResponseEntity<BlogPostEntity> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getPostById(id));
    }

    /*
     * Endpoint para obtener todos los posts o buscar por término.
     * Si se pasa un término como query param, realiza una búsqueda.
     * Si no, devuelve todos los posts.
     */
    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostEntity>> searchPosts(@RequestParam(value = "term", required = false) String term) {
        if (term != null && !term.isBlank()) {
            return ResponseEntity.ok(blogPostService.searchPosts(term));
        } else {
            return ResponseEntity.ok(blogPostService.getAllPosts());
        }
    }

    /*
     * Endpoint para actualizar un post existente por ID.
     * Recibe los datos nuevos y el ID del post a modificar.
     */
    @PutMapping("{id}")
    public ResponseEntity<BlogPostEntity> updatePost(@RequestBody @Valid BlogPostEntity newPost, @PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.updatePost(newPost, id));
    }

    /*
     * Endpoint para eliminar un post por su ID.
     * Devuelve un status 204 (No Content) si la operación fue exitosa.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
