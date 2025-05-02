package com.edu.fatec.restfull.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.fatec.restfull.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}