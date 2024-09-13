package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.BookService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UserService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.LoanSameBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SameBookLoan extends Handler {

    private final LoanRepository loanRepository;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public SameBookLoan(LoanRepository loanRepository, UserService userService, BookService bookService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void check(LoanRequestDTO data) {
        User currentUser = userService.getUsuarioById(data.usuarioId());
        Book currentBook = bookService.getBook(data.livroId());

        if (currentUser != null && currentBook != null) {
            var searchForLoan = loanRepository.findByUsuarioAndLivro(currentUser.getId(), currentBook.getId());

            if (searchForLoan.isPresent()) {
                throw new LoanSameBookException();
            }

            getNextHandler().check(data);
        }

    }
}
