package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UsuarioService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExiste extends Handler {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioExiste(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario usuario = usuarioService.getUsuarioById(data.usuarioId());

        if (getNextHandler() != null) {
            getNextHandler().check(data);
        }
    }
}
