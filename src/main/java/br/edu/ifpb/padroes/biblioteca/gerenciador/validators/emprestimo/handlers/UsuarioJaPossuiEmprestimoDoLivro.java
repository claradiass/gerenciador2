package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJaPossuiEmprestimoDoLivro extends BaseHandler {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    @Autowired
    public UsuarioJaPossuiEmprestimoDoLivro(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Optional<Usuario> currentUser = usuarioRepository.findById(data.usuarioId());
        Optional<Livro> currentLivro = livroRepository.findById(data.livroId());

        if (currentUser.isPresent() && currentLivro.isPresent()) {
            var searchForEmprestimo = emprestimoRepository.findByUsuarioAndLivro(currentUser.get().getId(), currentLivro.get().getId());
            if (searchForEmprestimo.isPresent()) {
                throw new RuntimeException("Você já possui um emprestimo desse mesmo livro.");
            }
        }
        checkNext(data);
    }
}
