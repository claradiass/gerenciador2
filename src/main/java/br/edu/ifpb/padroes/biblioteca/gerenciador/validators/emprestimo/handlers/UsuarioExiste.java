package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.UsuarioService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExiste extends BaseHandler {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioExiste(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void check(EmprestimoDTO data) {
        Usuario usuario = usuarioService.getUsuarioById(data.usuarioId());

        checkNext(data);
    }
}
