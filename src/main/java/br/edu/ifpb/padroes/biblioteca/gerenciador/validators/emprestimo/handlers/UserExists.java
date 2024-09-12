package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UserService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExists extends Handler {
    private final UserService userService;

    @Autowired
    public UserExists(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void check(LoanRequestDTO data) {
        userService.getUsuarioById(data.usuarioId());

        if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
