package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

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

    private final LoanRepository emprestimoRepository;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public SameBookLoan(LoanRepository emprestimoRepository, UserService userService, BookService bookService) {
        this.emprestimoRepository = emprestimoRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void check(LoanRequestDTO data) {
        User currentUser = userService.getUsuarioById(data.usuarioId());
        Book currentBook = bookService.getLivro(data.livroId());

        if (currentUser != null && currentBook != null) {
            var searchForEmprestimo = emprestimoRepository.findByUsuarioAndLivro(currentUser.getId(), currentBook.getId());

            if (searchForEmprestimo.isPresent()) {
                throw new LoanSameBookException();
            }

            getNextHandler().check(data);
        }

    }
}
