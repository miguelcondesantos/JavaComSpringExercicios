package com.edu.fatec.seguranca.adaptadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.fatec.seguranca.modelos.Perfil;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;
	private String senha;
	private Collection<? extends GrantedAuthority> autoridades;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(String nomeUsuario, String senha, List<Perfil> perfis) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.gerarAutoridades(perfis);
	}

	private void gerarAutoridades(List<Perfil> perfis) {
		List<SimpleGrantedAuthority> autoridadesPerfis = new ArrayList<>();
		for (Perfil perfil : perfis) {
			autoridadesPerfis.add(new SimpleGrantedAuthority(perfil.name()));
		}
		this.autoridades = autoridadesPerfis;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autoridades;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
