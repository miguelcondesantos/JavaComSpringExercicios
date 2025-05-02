package com.fatec.cadastro.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.cadastro.entidade.Produto;
import com.fatec.cadastro.repositorio.RepositorioProduto;

@RestController
public class ControleProduto {
	
	@Autowired //liga direto o método post e get
	private RepositorioProduto repositorio; //esse cara aqui vai se comunicar com o banco de dados
	
	@GetMapping("/produtos")
	public List<Produto> recuperarTodosProdutos(){
		return repositorio.findAll();
	}
	
	@PostMapping("/cadastrar/produto")
	public void cadastrarPrduto(@RequestBody Produto produto) {
		repositorio.save(produto);
	}
	
}
