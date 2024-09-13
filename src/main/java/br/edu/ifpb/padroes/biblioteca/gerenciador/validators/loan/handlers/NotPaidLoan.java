package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.NotPaidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotPaidLoan extends Handler {

    private final LoanRepository loanRepository;

    @Autowired
    public NotPaidLoan(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void check(LoanRequestDTO data) {
        List<Loan> loansNotPaid = loanRepository.findNotPaidEmprestimo(data.usuarioId());

        if (!loansNotPaid.isEmpty()) {
            throw new NotPaidException();
        } else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
