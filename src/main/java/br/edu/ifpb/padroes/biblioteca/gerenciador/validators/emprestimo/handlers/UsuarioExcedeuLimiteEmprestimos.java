package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UsuarioService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UsuarioExcedeuLimiteEmprestimos extends Handler {

    private static final int LIMIT = 3;

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioService service;

    @Autowired
    public UsuarioExcedeuLimiteEmprestimos(EmprestimoRepository emprestimoRepository, UsuarioService service) {
        this.emprestimoRepository = emprestimoRepository;
        this.service = service;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario user = service.getUsuarioById(data.usuarioId());

        List<Emprestimo> emprestimos = emprestimoRepository.findNotPaidEmprestimoByUsuarioId(user.getId());

        if (emprestimos.size() >= LIMIT) {
            throw new RuntimeException("Você possui 3 ou mais livros não devolvidos ainda.");
        }else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
