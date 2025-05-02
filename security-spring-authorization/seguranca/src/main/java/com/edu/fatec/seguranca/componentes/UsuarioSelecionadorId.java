package com.edu.fatec.seguranca.componentes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edu.fatec.seguranca.entidades.Usuario;

@Component
public class UsuarioSelecionadorId implements Selecionador<Usuario, Long> {

	@Override
	public Usuario selecionar(List<Usuario> objetos, Long identificador) {
		Usuario usuario = null;
		for (Usuario objeto : objetos) {
			if (objeto.getId().longValue() == identificador.longValue()) {
				usuario = objeto;
				break;
			}
		}
		return usuario;
	}
}