package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.BookService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.MinimumQuantityBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsufficientQuantityBook extends Handler {
    private final BookService service;

    @Autowired
    public InsufficientQuantityBook(BookService bookService) {
        this.service = bookService;
    }

    @Override
    public void check(LoanRequestDTO data) {
        Book book = service.getBook(data.livroId());

        if (book.getQuantity() < 1) {
            throw new MinimumQuantityBookException();
        }

        getNextHandler().check(data);
    }
}
