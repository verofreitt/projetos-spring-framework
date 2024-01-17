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

import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto novoProduto) {
        Produto produto = produtoRepository.save(novoProduto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produtoRepository.save(produto);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

