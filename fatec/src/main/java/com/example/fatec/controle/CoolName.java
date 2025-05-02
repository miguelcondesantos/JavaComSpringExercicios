package com.example.fatec.controle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fatec.modelo.Produto;

@RestController
public class CoolName {
	
	@PostMapping("/enviar")
	public void receberProduto(@RequestBody Produto p) {
		System.out.println(p.nome);
		System.out.println(p.preco);
	}
	
	
	@GetMapping("/")
	public String mensagem() {
		return "python > java";
	}
	
	@GetMapping("/produto")
	public Produto retorno() {
		Produto p = new Produto();
		p.nome = "tênis";
		p.preco = 2.20;
		
		return p;
	}

}
