package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.LoanLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceededLimitLoan extends Handler {

    private static final int LIMIT = 3;

    private final LoanRepository loanRepository;

    @Autowired
    public ExceededLimitLoan(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void check(LoanRequestDTO data) {
        List<Loan> loans = loanRepository.findNotRefundEmprestimo(data.usuarioId());

        if (loans.size() >= LIMIT) {
            throw new LoanLimitException();
        } else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
