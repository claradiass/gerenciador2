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
    // Injetar automaticamente a dependência
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

//    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
//        Usuario usuario = usuarioRepository.findById(emprestimoDTO.usuarioId())
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//        Livro livro = livroRepository.findById(emprestimoDTO.livroId())
//                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
//
//        Emprestimo emprestimo = new Emprestimo(emprestimoDTO, usuario, livro);
//        return repository.save(emprestimo);
//    }

    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
        Usuario usuario = usuarioRepository.findById(emprestimoDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Livro livro = livroRepository.findById(emprestimoDTO.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo(emprestimoDTO, usuario, livro);
        return repository.save(emprestimo);  // O `id` será gerado automaticamente
    }


    public Emprestimo getEmprestimoById(Long id){
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
        if (emprestimoOptional.isPresent()) {
            return emprestimoOptional.get();
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }

//    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
//
//
//        Optional<Emprestimo> emprestimoExistente = findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(
//                emprestimoDTO.usuario(),
//                emprestimoDTO.livro(),
//                emprestimoDTO.dataEmprestimo(),
//                emprestimoDTO.dataEntregaPrevista()
//        );
//
//        if (emprestimoExistente.isPresent()) {
//            // Empréstimo já existe, não criar um novo
//            return emprestimoExistente.get();
//        } else {
//            // Empréstimo não existe, criar um novo
//            return repository.save(new Emprestimo(emprestimoDTO));
//        }
//    }


    public Emprestimo deleteEmprestimo(Long id) {
        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            repository.deleteById(id);
            return emprestimo;
        }
        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
    }

//    public Emprestimo updateEmprestimo(Long id, EmprestimoDTO emprestimoDTO) {
//        Optional<Emprestimo> emprestimoOptional = repository.findById(id);
//
//        if (emprestimoOptional.isPresent()) {
//            Emprestimo emprestimo = emprestimoOptional.get();
//
//            emprestimo.setUsuario(emprestimoDTO.usuario());
//            emprestimo.setLivro(emprestimoDTO.livro());
//            emprestimo.setDataEmprestimo(emprestimoDTO.dataEmprestimo());
//            emprestimo.setDataEntregaPrevista(emprestimoDTO.dataEntregaPrevista());
//            emprestimo.setDataDevolucao(emprestimoDTO.dataDevolucao());
//            emprestimo.setMulta(emprestimoDTO.multa());
//            emprestimo.setPago(emprestimoDTO.pago());
//
//            return repository.save(emprestimo);
//        }
//        throw new RuntimeException("Empréstimo com ID " + id + " não encontrado.");
//    }


    public Optional<Emprestimo> findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(Usuario usuario, Livro livro, Date dataEmprestimo, Date dataEntregaPrevista){
        return repository.findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(usuario, livro, dataEmprestimo, dataEntregaPrevista);
    }
}

