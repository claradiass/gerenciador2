package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuantidadeLivrosInsuficiente extends BaseHandler {
    private final LivroRepository livroRepository;

    @Autowired
    public QuantidadeLivrosInsuficiente(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Livro livro = livroRepository.findById(data.livroId()).orElseThrow();

        if (livro.getQuantidade() < 1) {
            throw new RuntimeException("Quantidade de livro insuficiente");
        }
        checkNext(data);
    }
}
