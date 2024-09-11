package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.LivroService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UsuarioService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJaPossuiEmprestimoDoLivro extends Handler {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    @Autowired
    public UsuarioJaPossuiEmprestimoDoLivro(EmprestimoRepository emprestimoRepository, UsuarioService usuarioService, LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario currentUser = usuarioService.getUsuarioById(data.usuarioId());
        Livro currentLivro = livroService.getLivro(data.livroId());

        if (currentUser != null && currentLivro != null) {
            var searchForEmprestimo = emprestimoRepository.findByUsuarioAndLivro(currentUser.getId(), currentLivro.getId());
            if (searchForEmprestimo.isPresent()) {
                throw new RuntimeException("Você já possui um emprestimo desse mesmo livro.");
            }
        }else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
