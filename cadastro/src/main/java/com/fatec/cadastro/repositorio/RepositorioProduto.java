package com.fatec.cadastro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.cadastro.entidade.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Long> {

}
