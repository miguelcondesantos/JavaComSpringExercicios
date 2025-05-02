package com.edu.fatec.seguranca.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredencialDataTransferObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeUsuario;
	private String senha;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}