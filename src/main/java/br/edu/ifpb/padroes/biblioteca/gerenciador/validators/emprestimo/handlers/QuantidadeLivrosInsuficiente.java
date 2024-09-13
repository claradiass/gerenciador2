package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.LivroService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuantidadeLivrosInsuficiente extends Handler {
    private final LivroService service;

    @Autowired
    public QuantidadeLivrosInsuficiente(LivroService livroService) {
        this.service = livroService;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Livro livro = service.getLivro(data.livroId());

        if (livro.getQuantidade() < 1) {
            throw new RuntimeException("Quantidade de livro insuficiente");
        }else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
