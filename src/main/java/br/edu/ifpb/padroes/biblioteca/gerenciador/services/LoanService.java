package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanUpdateRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.ChainBuilder;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class LoanService {

    private final static double DAILY_LATE_FEE = 1;

    @Autowired
    private LoanRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ChainBuilder loanValidatorChain;

    public Loan insertLoan(LoanRequestDTO loanRequestDTO) {

        if (loanRequestDTO.usuarioId() == null || loanRequestDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }

        User user = userService.getUsuarioById(loanRequestDTO.usuarioId());
        Book book = bookService.getBook(loanRequestDTO.livroId());

        Handler chain = new ChainBuilder()
                .addHandler(new UserExists(userService))
                .addHandler(new BookExists(bookService))
                .addHandler(new ExceededLimitLoan(repository, userService))
                .addHandler(new SameBookLoan(repository, userService, bookService))
                .addHandler(new InsufficientQuantityBook(bookService))
                .addHandler(new NotPaidLoan(repository))
                .build();

        chain.check(loanRequestDTO);

        book.setQuantity(book.getQuantity() - 1);
        bookService.updateQuantityBook(book.getId(), book.getQuantity());

        Loan loan = new Loan(loanRequestDTO, user, book);
        return repository.save(loan);
    }

    public Loan getLoanById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteLoan(Long id) {
        Loan loan = getLoanById(id);
        repository.delete(loan);
    }

    public Loan updateLoan(Long id, LoanUpdateRequestDTO loanUpdateDTO) {
        Loan loan = getLoanById(id);

        loan.setEstimatedDate(loanUpdateDTO.dataEntregaPrevista());

        return repository.save(loan);
    }

    public Loan refundBook(Long id, LocalDate refundDate) {

        Loan loan = getLoanById(id);

        if (refundDate == null) {
            throw new RuntimeException("Data de devolução é requerida.");
        }

        if (loan.getRefundDate() != null) {
            throw new IllegalStateException("O livro já foi devolvido.");
        }

        setLateFee(loan, refundDate);

        loan.setRefundDate(refundDate);

        Book book = loan.getBook();
        bookService.updateQuantityBook(book.getId(), book.getQuantity() + 1);

        return repository.save(loan);
    }

    public void payLateFee(Long id) {
        Loan loan = getLoanById(id);

        if (loan.isPaid()) {
            throw new RuntimeException("Este emprestimo já foi pago.");
        }

        double fee = loan.getFee();

        if (fee <= 0) {
            throw new RuntimeException("Empréstimo sem pendências.");
        }

        loan.setPaid(true);

        repository.save(loan);
    }

    private void setLateFee(Loan loan, LocalDate refundDate) {
        double fee = 0.0;

        if (refundDate.isAfter(loan.getEstimatedDate())) {
            long daysLate = ChronoUnit.DAYS.between(loan.getEstimatedDate(), refundDate);
            fee = DAILY_LATE_FEE * daysLate;

            loan.setFee(fee);

            repository.save(loan);
        }
    }
}