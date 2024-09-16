package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.PendingLoanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LateRefundLoan extends Handler {

    private final LoanRepository loanRepository;

    @Autowired
    public LateRefundLoan(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void check(LoanRequestDTO data) {
        List<Loan> loans = loanRepository.findOverdueEmprestimo(data.usuarioId()).stream().toList();

        if (!loans.isEmpty()) {
            throw new PendingLoanException();
        } else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
