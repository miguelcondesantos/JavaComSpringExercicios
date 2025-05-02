package com.edu.fatec.seguranca.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.hateoas.RepresentationModel;

import com.edu.fatec.seguranca.modelos.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends RepresentationModel<Usuario> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Credencial credencial;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Perfil> Perfis = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Credencial getCredencial() {
		return credencial;
	}
	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}
	public List<Perfil> getPerfis() {
		return Perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		Perfis = perfis;
	}
	
	
}