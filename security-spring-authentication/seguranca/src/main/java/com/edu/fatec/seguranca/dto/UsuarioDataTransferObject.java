package com.edu.fatec.seguranca.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.fatec.seguranca.adaptadores.DataTransferObject;
import com.edu.fatec.seguranca.entidades.Credencial;
import com.edu.fatec.seguranca.entidades.Usuario;

import lombok.Data;

@Data
public class UsuarioDataTransferObject implements DataTransferObject<Usuario> {
	private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
	
	private Usuario usuario;
	private String nomeUsuario;
	private String senha;
	
	@Override
	public Usuario obter() {
		Credencial credencial = new Credencial();
		credencial.setNomeUsuario(nomeUsuario);
		credencial.setSenha(codificador.encode(senha));
		usuario.setCredencial(credencial);
		return this.usuario;
	}
}