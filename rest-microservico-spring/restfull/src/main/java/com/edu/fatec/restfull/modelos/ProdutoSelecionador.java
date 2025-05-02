package com.edu.fatec.restfull.modelos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edu.fatec.restfull.entidades.Produto;

@Component
public class ProdutoSelecionador {
	public Produto selecionar(List<Produto> produtos, long id) {
		Produto selecionado = null;
		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				selecionado = produto;
			}
		}
		return selecionado;
	}
}
