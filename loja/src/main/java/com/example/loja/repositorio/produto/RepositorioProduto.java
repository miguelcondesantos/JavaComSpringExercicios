package com.example.loja.repositorio.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loja.cadastro.produto.Produto;


public interface RepositorioProduto extends JpaRepository<Produto, Long> {

}
