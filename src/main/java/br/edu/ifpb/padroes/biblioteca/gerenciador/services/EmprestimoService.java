package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UpdateEmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.EmprestimoValidatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoValidatorChain emprestimoValidatorChain;


    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
        validarUsuarioELivroPorEmprestimoDTO(emprestimoDTO);

        Usuario usuario = usuarioService.getUsuarioById(emprestimoDTO.usuarioId());
        Livro livro = livroService.getLivro(emprestimoDTO.livroId());

        emprestimoValidatorChain.validate(emprestimoDTO);

        Emprestimo emprestimo = new Emprestimo(emprestimoDTO, usuario, livro);
        return repository.save(emprestimo);
    }


    public Emprestimo getEmprestimoById(Long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Emprestimo não encontrado."));
    }

    public void deletarEmprestimo(Long id) {
        Emprestimo emprestimo = getEmprestimoById(id);
        repository.delete(emprestimo);
    }

    public Emprestimo updateEmprestimo(Long id, UpdateEmprestimoDTO data) {
        Emprestimo emprestimo = getEmprestimoById(id);

        emprestimo.setDataEntregaPrevista(data.dataEntregaPrevista());

        return repository.save(emprestimo);

    }

    public List<Emprestimo> getEmprestimoByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    public List<Emprestimo> getEmprestimoByLivroId(Long id) {
        return repository.findByUsuarioId(id);
    }

    private void validarUsuarioELivroPorEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
        if (emprestimoDTO.usuarioId() == null || emprestimoDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }
    }
}



