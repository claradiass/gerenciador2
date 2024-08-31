package br.edu.ifpb.padroes.biblioteca.gerenciador.repositories;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Optional<Emprestimo> findByUsuarioAndLivroAndDataEmprestimoAndDataEntregaPrevista(Usuario usuario, Livro livro, Date dataEmprestimo, Date dataEntregaPrevista);
}
