package com.edu.fatec.restfull.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fatec.restfull.entidades.Produto;
import com.edu.fatec.restfull.modelos.AdicionadorLinkProduto;
import com.edu.fatec.restfull.modelos.ProdutoAtualizador;
import com.edu.fatec.restfull.modelos.ProdutoSelecionador;
import com.edu.fatec.restfull.repositorios.ProdutoRepositorio;

@RestController
public class ProdutoControle{
	@Autowired
	private ProdutoRepositorio repositorio;
	@Autowired
	private ProdutoSelecionador selecionador;
	@Autowired
	private AdicionadorLinkProduto adiconadorLink;
	
	
	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> obterProdutos(){
		List<Produto> produtos = repositorio.findAll();
		if(produtos.isEmpty()) {
			ResponseEntity<List<Produto>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		}else {
			adiconadorLink.adicionarLink(produtos);
			ResponseEntity<List<Produto>> resposta = new ResponseEntity<>(produtos, HttpStatus.OK);
			return resposta;
		}
		
	}
	
	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> obterProduto(@PathVariable long id) {
		List<Produto> produtos = repositorio.findAll();
		Produto produto = selecionador.selecionar(produtos, id);
		if (produto == null) {
			ResponseEntity<Produto> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			adiconadorLink.adicionarLink(produto);
			ResponseEntity<Produto> resposta = new ResponseEntity<Produto>(produto, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	@PostMapping("/produto/cadastro")
	public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (produto.getId() == null) {
			repositorio.save(produto);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);

	}

	@PutMapping("/produto/atualizar")
	public ResponseEntity<?> atualizarProduto(@RequestBody Produto atualizacao) {
		HttpStatus status = HttpStatus.CONFLICT;
		Produto produto = repositorio.getById(atualizacao.getId());
		if (produto != null) {
			ProdutoAtualizador atualizador = new ProdutoAtualizador();
			atualizador.atualizar(produto, atualizacao);
			repositorio.save(produto);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}
	
	
	@DeleteMapping("/produto/excluir")
	public ResponseEntity<?> excluirCliente(@RequestBody Produto exclusao) {
		Produto produto = repositorio.getById(exclusao.getId());
		repositorio.delete(produto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
