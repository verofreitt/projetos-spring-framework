package com.generation.gamestore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gamestore.model.Categoria;
import com.generation.gamestore.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria novaCategoria) {
        Categoria categoria = categoriaRepository.save(novaCategoria);
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNome(categoriaAtualizada.getNome());
            categoriaRepository.save(categoria);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

