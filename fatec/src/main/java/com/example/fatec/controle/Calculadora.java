package com.example.fatec.controle;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fatec.modelo.Dados;
import com.example.fatec.modelo.Resultado;

@RestController
public class Calculadora {
	@PostMapping("/somar")
	public Resultado somar(@RequestBody Dados dados) {
		Resultado resultado =  new Resultado();
		resultado.valor = dados.valor1 + dados.valor2;
		return resultado;
	}
	

	@PostMapping("/sub")
	public Resultado sub(@RequestBody Dados dados) {
		Resultado resultado =  new Resultado();
		resultado.valor = dados.valor1 - dados.valor2;
		return resultado;
	}
	
	@PostMapping("/dividir")
	public Resultado dividir(@RequestBody Dados dados) {
		Resultado resultado =  new Resultado();
		resultado.valor = dados.valor1 / dados.valor2;
		return resultado;
	}
	
	@PostMapping("/multiplicar")
	public Resultado multiplicar(@RequestBody Dados dados) {
		Resultado resultado =  new Resultado();
		resultado.valor = dados.valor1 * dados.valor2;
		return resultado;
	}
}	
