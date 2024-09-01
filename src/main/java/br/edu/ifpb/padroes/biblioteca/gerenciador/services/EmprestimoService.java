package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
        if (emprestimoDTO.usuarioId() == null || emprestimoDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }

        Usuario usuario = findUsuarioByID(emprestimoDTO);
        Livro livro = findLivroByID(emprestimoDTO);

        Optional<Emprestimo> emprestimoExistente = findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(
                usuario,
                livro,
                emprestimoDTO.dataEmprestimo(),
                emprestimoDTO.dataEntregaPrevista()
        );

        if (emprestimoExistente.isEmpty()) {
            Emprestimo emprestimo = new Emprestimo(emprestimoDTO, usuario, livro);
            return repository.save(emprestimo);

        }
        throw new RuntimeException("Empréstimo já existente.");
    }


    public Emprestimo getEmprestimoById(Long id){
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
        if (emprestimoOptional.isPresent()) {
            return emprestimoOptional.get();
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
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

        Usuario usuario = findUsuarioByID(emprestimoDTO);
        Livro livro = findLivroByID(emprestimoDTO);

        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();

            emprestimo.setUsuario(usuario);
            emprestimo.setLivro(livro);
            emprestimo.setDataEmprestimo(emprestimoDTO.dataEmprestimo());
            emprestimo.setDataEntregaPrevista(emprestimoDTO.dataEntregaPrevista());
            emprestimo.setDataDevolucao(emprestimoDTO.dataDevolucao());
            emprestimo.setMulta(emprestimoDTO.multa());
            emprestimo.setPago(emprestimoDTO.pago());

            return repository.save(emprestimo);
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }


    public Optional<Emprestimo> findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(Usuario usuario, Livro livro, Date dataEmprestimo, Date dataEntregaPrevista){
        return repository.findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(usuario, livro, dataEmprestimo, dataEntregaPrevista);
    }

    public Usuario findUsuarioByID(EmprestimoDTO emprestimoDTO){
        return  usuarioRepository.findById(emprestimoDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Livro findLivroByID(EmprestimoDTO emprestimoDTO){
        return livroRepository.findById(emprestimoDTO.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
}



