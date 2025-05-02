package com.edu.fatec.restfull.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.edu.fatec.restfull.controles.ProdutoControle;
import com.edu.fatec.restfull.entidades.Produto;

@Component
public class AdicionadorLinkProduto implements AdicionadorLink<Produto>{

	@Override
	public void adicionarLink(List<Produto> lista) {
		for (Produto produto : lista) {
			long id = produto.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ProdutoControle.class)
							.obterProduto(id))
					.withSelfRel();
			produto.add(linkProprio);
		}
	}

		

	@Override
	public void adicionarLink(Produto objeto) {
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ProdutoControle.class)
						.obterProdutos())
				.withRel("Lista de produtos");
		objeto.add(linkProprio);
}
	

}
