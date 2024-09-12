package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

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

    private final LoanRepository emprestimoRepository;
    private final UserService service;

    @Autowired
    public ExceededLimitLoan(LoanRepository emprestimoRepository, UserService service) {
        this.emprestimoRepository = emprestimoRepository;
        this.service = service;
    }

    @Override
    public void check(LoanRequestDTO data) {
        User user = service.getUsuarioById(data.usuarioId());

        List<Loan> loans = emprestimoRepository.findNotRefundEmprestimo(user.getId());

        if (loans.size() >= LIMIT) {
            throw new LoanLimitException();
        }

        getNextHandler().check(data);
    }
}
