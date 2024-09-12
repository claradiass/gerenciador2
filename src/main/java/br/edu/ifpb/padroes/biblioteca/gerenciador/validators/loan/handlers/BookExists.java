package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.BookService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookExists extends Handler {
    private final BookService bookService;

    @Autowired
    public BookExists(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void check(LoanRequestDTO data) {
        bookService.getBook(data.livroId());

        if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}

