package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class EmprestimoValidatorChain {
    private final BaseHandler firstHandler;

    @Autowired
    public EmprestimoValidatorChain(BaseHandler ... handlers) {
        this.firstHandler = BaseHandler.link(handlers);
    }

    public void validate(EmprestimoDTO emprestimoDTO) {
        firstHandler.check(emprestimoDTO);
    }
}
