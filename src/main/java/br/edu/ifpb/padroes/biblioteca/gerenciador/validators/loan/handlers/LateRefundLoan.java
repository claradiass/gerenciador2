package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UserRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.PendingLoanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LateRefundLoan extends Handler {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Autowired
    public LateRefundLoan(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void check(LoanRequestDTO data) {
        User user = userRepository.findById(data.usuarioId()).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        List<Loan> loans = loanRepository.findOverdueEmprestimo(user.getId()).stream().toList();

        if (!loans.isEmpty()) {
            throw new PendingLoanException();
        }else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
