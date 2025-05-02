package com.edu.fatec.seguranca.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.fatec.seguranca.adaptadores.UserDetailsImpl;
import com.edu.fatec.seguranca.componentes.UsuarioSelecionadorNomeUsuario;
import com.edu.fatec.seguranca.entidades.Credencial;
import com.edu.fatec.seguranca.entidades.Usuario;
import com.edu.fatec.seguranca.repositorios.UsuarioRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio repositorio;

	@Autowired
	private UsuarioSelecionadorNomeUsuario selecionador;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarios = repositorio.findAll();
		Usuario usuario = selecionador.selecionar(usuarios, username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		Credencial credencial = usuario.getCredencial();
		return new UserDetailsImpl(credencial.getNomeUsuario(), credencial.getSenha(), usuario.getPerfis());
	}
}