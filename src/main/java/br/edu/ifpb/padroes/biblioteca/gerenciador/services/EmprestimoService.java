package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RequestPagamentoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UpdateEmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.EmprestimoRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.EmprestimoValidatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Service
public class EmprestimoService {

    private final static double RATE = 1;

    private final static Calendar calendar = Calendar.getInstance();
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

    public Emprestimo devolverLivro(Long id, Date dataDevolucao) {

        Emprestimo emprestimo = getEmprestimoById(id);

        if (dataDevolucao == null) {
            throw new RuntimeException("Data de devolução é requerida.");
        }

        if (emprestimo.getDataDevolucao() != null) {
            throw new IllegalStateException("O livro já foi devolvido.");
        }

        if (!emprestimo.isPago()) {
            throw new RuntimeException("O livro não pode ser devolvido.");
        }

        emprestimo.setDataDevolucao(dataDevolucao);

        Livro livro = emprestimo.getLivro();
        livroService.updateQuantidadeLivro(livro.getId(), livro.getQuantidade() + 1);

        return repository.save(emprestimo);
    }

    public void pagarMulta(RequestPagamentoDTO pagamentoDTO) {
        Emprestimo emprestimo = repository.findByUsuarioAndLivro(pagamentoDTO.usuarioId(), pagamentoDTO.livroId()).orElseThrow(() -> new NoSuchElementException("Este empréstimo já foi pago!"));

        double multa = emprestimo.getMulta();

        if (multa <= 0) { throw new RuntimeException("Empréstimo sem pendências."); }

        emprestimo.setPago(true);

        repository.save(emprestimo);
    }

    private void validarUsuarioELivroPorEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
        if (emprestimoDTO.usuarioId() == null || emprestimoDTO.livroId() == null) {
            throw new IllegalArgumentException("IDs de usuário e livro não podem ser nulos");
        }
    }

    private double calcularMulta(Emprestimo emprestimo, Date dataDevolucao) {
        long dataPrevistaMillis = emprestimo.getDataEntregaPrevista().getTime();
        long dataDevolucaoMillis = dataDevolucao.getTime();

        long diferencaMillis = dataDevolucaoMillis - dataPrevistaMillis;

        long diasDeAtraso = TimeUnit.DAYS.convert(diferencaMillis, TimeUnit.MILLISECONDS) + 1;

        if (diasDeAtraso > 0) {
            double multa = RATE * diasDeAtraso;

            emprestimo.setMulta(multa);

            repository.save(emprestimo);

            return multa;
        }

        return 0;
    }

    public List<Emprestimo> getEmprestimoByUsuarioId(Long id) {
        return repository.findByUsuarioId(id);
    }

    public List<Emprestimo> getEmprestimoByLivroId(Long id) {
        return repository.findByUsuarioId(id);
    }
}



