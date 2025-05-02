package com.edu.fatec.seguranca.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.edu.fatec.seguranca.filtros.JwtFiltroAutenticador;
import com.edu.fatec.seguranca.filtros.JwtFiltroAutorizador;
import com.edu.fatec.seguranca.jwt.JsonWebTotenGerador;
import com.edu.fatec.seguranca.servicos.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl servico;

	@Autowired
	private JsonWebTotenGerador jwtTokenGerador;

	private static final String[] rotasPublicas = { "/" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();

		http.authorizeHttpRequests().antMatchers(rotasPublicas).permitAll().anyRequest().authenticated();

		http.addFilter(new JwtFiltroAutenticador(authenticationManager(), jwtTokenGerador));
		http.addFilter(new JwtFiltroAutorizador(authenticationManager(), jwtTokenGerador, servico));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder autenticador) throws Exception {
		autenticador.userDetailsService(servico).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
		fonte.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return fonte;
	}
}
