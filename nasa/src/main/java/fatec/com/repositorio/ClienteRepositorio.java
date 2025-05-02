package fatec.com.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.com.entidade.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

}
