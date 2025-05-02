package com.edu.fatec.restfull.modelos;

import com.edu.fatec.restfull.entidades.Produto;

public class ProdutoAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();
	private NumberVerificadorNulo verificador2 = new NumberVerificadorNulo();
	
	private void atualizarDados(Produto produto, Produto atualizacao) {
		if (!verificador.verificar(atualizacao.getNome())) {
			produto.setNome(atualizacao.getNome());
		}
		if (!verificador2.verificar(atualizacao.getPreco())) {
			produto.setPreco(atualizacao.getPreco());
		}
	}

	public void atualizar(Produto produto, Produto atualizacao) {
		atualizarDados(produto, atualizacao);
	}
}
