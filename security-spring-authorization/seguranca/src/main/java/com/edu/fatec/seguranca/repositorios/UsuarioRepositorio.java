package com.edu.fatec.seguranca.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.fatec.seguranca.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}