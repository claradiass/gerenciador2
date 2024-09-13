package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DevolucaoEmprestimoAtrasado extends Handler {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DevolucaoEmprestimoAtrasado(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario user = usuarioRepository.findById(data.usuarioId()).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        List<Emprestimo> emprestimos = emprestimoRepository.findOverdueEmprestimo(user.getId()).stream().toList();

        if (!emprestimos.isEmpty()) {
            throw new RuntimeException("Você precisa devolver os empréstimos pendentes para realizar novos empréstimos.");
        }else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
