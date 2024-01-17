package com.generation.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.gamestore.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
