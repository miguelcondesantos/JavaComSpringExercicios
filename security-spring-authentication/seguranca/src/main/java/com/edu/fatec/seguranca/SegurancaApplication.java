package com.edu.fatec.seguranca;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.edu.fatec.seguranca.entidades.Credencial;
import com.edu.fatec.seguranca.entidades.Usuario;
import com.edu.fatec.seguranca.modelos.Perfil;
import com.edu.fatec.seguranca.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class SegurancaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SegurancaApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public UsuarioRepositorio repositorio;

		public final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2002, 05, 15);

			for (int i = 0; i < 1; i++) {
				Usuario usuario = new Usuario();
				usuario.setNome("Pedro Alcântara de Bragança e Bourbon");

				Credencial credencial = new Credencial();
				credencial.setNomeUsuario("dompedro");
				credencial.setSenha(codificador.encode("123456"));
				
				usuario.getPerfis().add(Perfil.ROLE_ADMIN);

				usuario.setCredencial(credencial);

				repositorio.save(usuario);
			}
		}
	}
}