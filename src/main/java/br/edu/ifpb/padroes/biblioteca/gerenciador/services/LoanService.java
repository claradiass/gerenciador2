package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.PaymentRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanUpdateRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.ChainBuilder;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    private ChainBuilder emprestimoValidatorChain;

    public Loan insertEmprestimo(LoanRequestDTO loanRequestDTO) {
        validarUsuarioELivroPorEmprestimoDTO(loanRequestDTO);

        User usuario = userService.getUsuarioById(loanRequestDTO.usuarioId());
        Book book = bookService.getLivro(loanRequestDTO.livroId());

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
        bookService.updateQuantidadeLivro(book.getId(), book.getQuantity());

        Loan loan = new Loan(loanRequestDTO, usuario, book);
        return repository.save(loan);
    }

    public Loan getEmprestimoById(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void deletarEmprestimo(Long id) {
        Loan loan = getEmprestimoById(id);
        repository.delete(loan);
    }

    public Loan updateEmprestimo(Long id, LoanUpdateRequestDTO data) {
        Loan loan = getEmprestimoById(id);

        loan.setEstimatedDate(data.dataEntregaPrevista());

        return repository.save(loan);

    }

    public Loan devolverLivro(Long id, LocalDate dataDevolucao) {

        Loan loan = getEmprestimoById(id);

        if (dataDevolucao == null) {
            throw new RuntimeException("Data de devolução é requerida.");
        }

        if (loan.getRefundDate() != null) {
            throw new IllegalStateException("O livro já foi devolvido.");
        }

        calcularMulta(loan, dataDevolucao);

        loan.setRefundDate(dataDevolucao);

        Book book = loan.getLivro();
        bookService.updateQuantidadeLivro(book.getId(), book.getQuantity() + 1);

        return repository.save(loan);
    }

    public void pagarMulta(Long id, PaymentRequestDTO pagamentoDTO) {
        Loan loan = getEmprestimoById(id);

        if (loan.isPaid()) {
            throw new RuntimeException("Este emprestimo já foi pago.");
        }

        double multa = loan.getFee();

        if (multa <= 0) {
            throw new RuntimeException("Empréstimo sem pendências.");
        }

        loan.setPaid(true);
        repository.save(loan);
    }

    private void validarUsuarioELivroPorEmprestimoDTO(LoanRequestDTO loanRequestDTO) {
        if (loanRequestDTO.usuarioId() == null || loanRequestDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }
    }

    private void calcularMulta(Loan loan, LocalDate dataDevolucao) {
        double multa = 0.0;

        if (dataDevolucao.isAfter(loan.getEstimatedDate())) {
            long diasDeAtraso = ChronoUnit.DAYS.between(loan.getEstimatedDate(), dataDevolucao);
            multa = DAILY_LATE_FEE * diasDeAtraso;
            loan.setFee(multa);
            repository.save(loan);
        }

    }

    public List<Loan> getEmprestimoByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    public List<Loan> getEmprestimoByLivroId(Long id) {
        return repository.findByUsuarioId(id);
    }
}