package com.edu.fatec.restfull.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.fatec.restfull.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
	

}
