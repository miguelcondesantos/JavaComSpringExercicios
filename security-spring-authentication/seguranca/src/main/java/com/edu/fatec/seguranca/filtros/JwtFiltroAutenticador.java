package com.edu.fatec.seguranca.filtros;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edu.fatec.seguranca.dto.CredencialDataTransferObject;
import com.edu.fatec.seguranca.jwt.JsonWebTotenGerador;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtFiltroAutenticador extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager gerenciadorAutenticacao;
	private JsonWebTotenGerador jwtTokenGerador;

	public JwtFiltroAutenticador(AuthenticationManager gerenciadorAutenticacao, JsonWebTotenGerador jwtTokenGerador) {
		this.gerenciadorAutenticacao = gerenciadorAutenticacao;
		this.jwtTokenGerador = jwtTokenGerador;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			CredencialDataTransferObject credencialDto = new ObjectMapper().readValue(request.getInputStream(),
					CredencialDataTransferObject.class);
			UsernamePasswordAuthenticationToken tokenAutenticacao = new UsernamePasswordAuthenticationToken(
					credencialDto.getNomeUsuario(), credencialDto.getSenha(), new ArrayList<>());

			Authentication autenticacao = gerenciadorAutenticacao.authenticate(tokenAutenticacao);
			return autenticacao;

		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication resultadoAutenticacao) throws IOException, ServletException {
		UserDetails usuarioSpring = (UserDetails) resultadoAutenticacao.getPrincipal();
		String nomeUsuario = usuarioSpring.getUsername();
		String jwtToken = jwtTokenGerador.gerarToken(nomeUsuario);

		response.addHeader("Authorization", "Bearer " + jwtToken);

	}

}
