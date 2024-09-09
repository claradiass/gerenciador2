package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class PagamentoEmprestimoAtrasado extends BaseHandler {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PagamentoEmprestimoAtrasado(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario user = usuarioRepository.findById(data.usuarioId()).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        List<Emprestimo> emprestimos = emprestimoRepository.findOverdueEmprestimo(user.getId()).stream().toList();

        if (!emprestimos.isEmpty()) {
            throw new RuntimeException("Para fazer um empréstimo, você precisa devolver os empréstimos pendentes.");
        }

        checkNext(data);
    }
}
