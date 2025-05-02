package com.edu.fatec.seguranca.componentes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.edu.fatec.seguranca.entidades.Credencial;
import com.edu.fatec.seguranca.entidades.Usuario;

@Component
public class UsuarioSelecionadorNomeUsuario implements Selecionador<Usuario, String> {

	@Override
	public Usuario selecionar(List<Usuario> objetos, String identificador) {
		Usuario usuario = null;
		for (Usuario objeto : objetos) {
			Credencial credencial = objeto.getCredencial();
			String nomeUsuario = credencial.getNomeUsuario();
			if (nomeUsuario.trim().equals(identificador.trim())) {
				usuario = objeto;
				break;
			}
		}
		return usuario;
	}
}