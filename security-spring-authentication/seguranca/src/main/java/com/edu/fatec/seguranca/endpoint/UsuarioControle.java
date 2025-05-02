package com.edu.fatec.seguranca.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fatec.seguranca.componentes.UsuarioSelecionadorId;
import com.edu.fatec.seguranca.dto.UsuarioDataTransferObject;
import com.edu.fatec.seguranca.entidades.Usuario;
import com.edu.fatec.seguranca.hateoas.UsuarioHateoas;
import com.edu.fatec.seguranca.repositorios.UsuarioRepositorio;

@RestController
public class UsuarioControle {

	@Autowired
	private UsuarioRepositorio repositorio;

	@Autowired
	private UsuarioHateoas hateoas;

	@Autowired
	private UsuarioSelecionadorId selecionador;

	@Autowired

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> obterClientes() {
		List<Usuario> clientes = repositorio.findAll();
		if (clientes.isEmpty()) {
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			hateoas.adicionarLink(clientes);
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(clientes, HttpStatus.FOUND);
			return resposta;
		}
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> obterCliente(@PathVariable long id) {
		List<Usuario> usuarios = repositorio.findAll();
		Usuario usuario = selecionador.selecionar(usuarios, id);
		if (usuario == null) {
			ResponseEntity<Usuario> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			hateoas.adicionarLink(usuario);
			ResponseEntity<Usuario> resposta = new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
			return resposta;
		}
	}

	@PostMapping("/usuario/cadastrar")
	public ResponseEntity<Usuario> cadastrarCliente(@RequestBody UsuarioDataTransferObject usuarioDto) {
		ResponseEntity<Usuario> resposta = new ResponseEntity<>(HttpStatus.CREATED);
		try {
			Usuario usuario = usuarioDto.obter();
			repositorio.save(usuario);
		} catch (Exception e) {
			resposta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resposta;
	}
}