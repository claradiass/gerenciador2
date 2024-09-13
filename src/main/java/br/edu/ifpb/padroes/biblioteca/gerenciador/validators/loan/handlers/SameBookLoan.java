package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.loan.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LoanRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions.LoanSameBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SameBookLoan extends Handler {

    private final LoanRepository loanRepository;

    @Autowired
    public SameBookLoan(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void check(LoanRequestDTO data) {
        var searchForLoan = loanRepository.findByUsuarioAndLivro(data.usuarioId(), data.livroId());

        if (searchForLoan.isPresent()) {
            throw new LoanSameBookException();
        } else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
