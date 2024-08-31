package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmprestimoService {


    @Autowired
    // Injetar automaticamente a dependência
    private EmprestimoRepository repository;

    public Emprestimo getEmprestimoById(Long id){
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
        if (emprestimoOptional.isPresent()) {
            return emprestimoOptional.get();
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }

    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {

        if (!existsEmprestimo(emprestimoDTO)) {
            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setUsuario(emprestimoDTO.usuario());
            novoEmprestimo.setLivro(emprestimoDTO.livro());
            novoEmprestimo.setDataEmprestimo(emprestimoDTO.dataEmprestimo());
            novoEmprestimo.setDataEntregaPrevista(emprestimoDTO.dataEntregaPrevista());
            novoEmprestimo.setDataDevolucao(emprestimoDTO.dataDevolucao());
            novoEmprestimo.setMulta(emprestimoDTO.multa());
            novoEmprestimo.setPago(emprestimoDTO.pago());

            return repository.save(novoEmprestimo);
        }
        throw new RuntimeException("Empréstimo já existente.");
    }


    public Emprestimo deleteEmprestimo(Long id) {
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            repository.deleteById(id);
            return emprestimo;
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }

    public Emprestimo updateEmprestimo(Long id, EmprestimoDTO emprestimoDTO) {
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);

        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();

            emprestimo.setUsuario(emprestimoDTO.usuario());
            emprestimo.setLivro(emprestimoDTO.livro());
            emprestimo.setDataEmprestimo(emprestimoDTO.dataEmprestimo());
            emprestimo.setDataEntregaPrevista(emprestimoDTO.dataEntregaPrevista());
            emprestimo.setDataDevolucao(emprestimoDTO.dataDevolucao());
            emprestimo.setMulta(emprestimoDTO.multa());
            emprestimo.setPago(emprestimoDTO.pago());

            return repository.save(emprestimo);
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }


    public boolean existsEmprestimo(EmprestimoDTO emprestimoDTO){
        return repository.existsEmprestimo(emprestimoDTO);
    }
}

