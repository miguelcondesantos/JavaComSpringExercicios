package fatec.com.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fatec.com.entidade.Cliente;
import fatec.com.repositorio.ClienteRepositorio;

@RestController
public class ClienteControle {
    private final ClienteRepositorio repositorio;

    @Autowired
    public ClienteControle(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody Cliente cliente) {
        repositorio.save(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> obterClientes() {
        return repositorio.findAll();
    }
}
