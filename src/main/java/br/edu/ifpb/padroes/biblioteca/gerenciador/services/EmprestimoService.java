package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RequestPagamentoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UpdateEmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.ChainBuilder;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {

    private final static double DAILY_LATE_FEE = 1;

    private final static Calendar calendar = Calendar.getInstance();
    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private ChainBuilder emprestimoValidatorChain;

    public Emprestimo insertEmprestimo(EmprestimoDTO emprestimoDTO) {
        validarUsuarioELivroPorEmprestimoDTO(emprestimoDTO);

        Usuario usuario = usuarioService.getUsuarioById(emprestimoDTO.usuarioId());
        Livro livro = livroService.getLivro(emprestimoDTO.livroId());

        Handler chain = new ChainBuilder()
                .addHandler(new UsuarioExiste(usuarioService))
                .addHandler(new LivroExiste(livroService))
                .addHandler(new UsuarioJaPossuiEmprestimoDoLivro(repository, usuarioService, livroService))
                .addHandler(new UsuarioExcedeuLimiteEmprestimos(repository, usuarioService))
                .addHandler(new QuantidadeLivrosInsuficiente(livroService))
                .build();

        chain.check(emprestimoDTO);

        livro.setQuantidade(livro.getQuantidade() - 1);
        livroService.updateQuantidadeLivro(livro.getId(), livro.getQuantidade());

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

    public Emprestimo devolverLivro(Long id, LocalDate dataDevolucao) {

        Emprestimo emprestimo = getEmprestimoById(id);

        if (dataDevolucao == null) {
            throw new RuntimeException("Data de devolução é requerida.");
        }

        if (emprestimo.getDataDevolucao() != null) {
            throw new IllegalStateException("O livro já foi devolvido.");
        }

        if (!emprestimo.isPago()) {
            throw new RuntimeException("Você precisar pagar a multa de atraso primeiro.");
        }

        emprestimo.setDataDevolucao(dataDevolucao);

        Livro livro = emprestimo.getLivro();
        livroService.updateQuantidadeLivro(livro.getId(), livro.getQuantidade() + 1);

        return repository.save(emprestimo);
    }

    public void pagarMulta(Long id, RequestPagamentoDTO pagamentoDTO) {
        Emprestimo emprestimo = getEmprestimoById(id);

//        if (emprestimo.getDataDevolucao() == null) {
//            throw new RuntimeException("Você não pode pagar este emprestimo, pois o livro ainda não foi devolvido.");
//        }

        if (emprestimo.isPago()) {
            throw new RuntimeException("Este emprestimo já foi pago.");
        }

        double multa = calcularMulta(emprestimo, pagamentoDTO.dataDevolucao());

        if (multa <= 0) {
            throw new RuntimeException("Empréstimo sem pendências.");
        }

        emprestimo.setPago(true);
        repository.save(emprestimo);
    }

    private void validarUsuarioELivroPorEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
        if (emprestimoDTO.usuarioId() == null || emprestimoDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }
    }

    private double calcularMulta(Emprestimo emprestimo, LocalDate dataDevolucao) {
        double multa = 0.0;

        if (dataDevolucao.isAfter(emprestimo.getDataEntregaPrevista())) {
            long diasDeAtraso = ChronoUnit.DAYS.between(emprestimo.getDataEntregaPrevista(), dataDevolucao);
            multa = DAILY_LATE_FEE * diasDeAtraso;
            emprestimo.setMulta(multa);
            repository.save(emprestimo);
        }

        return multa;
    }

    // Pra que esses metodos aq ??

    public List<Emprestimo> getEmprestimoByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    public List<Emprestimo> getEmprestimoByLivroId(Long id) {
        return repository.findByUsuarioId(id);
    }
}