package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.EmprestimoService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class PagamentoEmprestimoNaoRealizado extends Handler {

    private EmprestimoRepository emprestimoRepository;

    @Autowired
    public PagamentoEmprestimoNaoRealizado(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }
    @Override
    public void check(EmprestimoDTO data) {
        List<Emprestimo> emprestimosNotPaid = emprestimoRepository.findNotPaidEmprestimo(data.usuarioId());

        if (!emprestimosNotPaid.isEmpty()) {
            throw new RuntimeException("O usuário tem multas pendentes.");
        } else if (getNextHandler() != null) {
            getNextHandler().check(data);
        }

    }
}
