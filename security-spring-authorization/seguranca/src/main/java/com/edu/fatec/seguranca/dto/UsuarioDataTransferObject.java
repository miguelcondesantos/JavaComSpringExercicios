package com.edu.fatec.seguranca.dto;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.fatec.seguranca.adaptadores.DataTransferObject;
import com.edu.fatec.seguranca.entidades.Credencial;
import com.edu.fatec.seguranca.entidades.Usuario;
import com.edu.fatec.seguranca.modelos.Perfil;

import lombok.Data;

@Data
public class UsuarioDataTransferObject implements DataTransferObject<Usuario> {
	private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();
	
	private Usuario usuario;
	private String nomeUsuario;
	private String senha;
	private List<Perfil> perfis;
	
	@Override
	public Usuario obter() {
		Credencial credencial = new Credencial();
		credencial.setNomeUsuario(nomeUsuario);
		credencial.setSenha(codificador.encode(senha));
		usuario.setCredencial(credencial);
		usuario.setPerfis(perfis);
		return this.usuario;
	}
}