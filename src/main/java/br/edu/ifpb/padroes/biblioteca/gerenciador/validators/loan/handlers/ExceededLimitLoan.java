package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UserService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.LoanLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceededLimitLoan extends Handler {

    private static final int LIMIT = 3;

    private final LoanRepository loanRepository;
    private final UserService service;

    @Autowired
    public ExceededLimitLoan(LoanRepository loanRepository, UserService service) {
        this.loanRepository = loanRepository;
        this.service = service;
    }

    @Override
    public void check(LoanRequestDTO data) {
        User user = service.getUsuarioById(data.usuarioId());

        List<Loan> loans = loanRepository.findNotRefundEmprestimo(user.getId());

        if (loans.size() >= LIMIT) {
            throw new LoanLimitException();
        }

        getNextHandler().check(data);
    }
}
