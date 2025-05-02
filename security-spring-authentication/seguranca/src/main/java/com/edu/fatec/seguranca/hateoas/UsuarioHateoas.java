package com.edu.fatec.seguranca.hateoas;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.edu.fatec.seguranca.endpoint.UsuarioControle;
import com.edu.fatec.seguranca.entidades.Usuario;

@Component
public class UsuarioHateoas implements Hateoas<Usuario> {

	@Override
	public void adicionarLink(List<Usuario> lista) {
		for (Usuario cliente : lista) {
			long id = cliente.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UsuarioControle.class)
							.obterCliente(id))
					.withSelfRel();
			cliente.add(linkProprio);
		}
	}

	@Override
	public void adicionarLink(Usuario objeto) {
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.obterClientes())
				.withRel("clientes");
		objeto.add(linkProprio);
	}
}