package com.example.loja.controle.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loja.cadastro.produto.Produto;
import com.example.loja.repositorio.produto.RepositorioProduto;

@RestController
public class ControleProduto {
	@Autowired
	private RepositorioProduto repositorio;
	
	@GetMapping("/produtos")
	public List<Produto> recuperarTodosProdutos(){
		return repositorio.findAll();
	}
	
	@PostMapping("/cadastrar/produto")
	public void cadastrarPrduto(@RequestBody Produto produto) {
		repositorio.save(produto);
	}
	
	@DeleteMapping("delete/produto/{id}")
	public void excluirProduto(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	
	@PutMapping("/atualizar/produto/{id}")
    public void atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoNovo) {
        Produto produto = repositorio.findById(id).orElse(null);
        if (produto != null) {
            produto.setNome(produtoNovo.getNome());
            produto.setPreco(produtoNovo.getPreco());
            repositorio.save(produto);
        }
    }
	

}
