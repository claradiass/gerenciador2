package br.edu.ifpb.padroes.biblioteca.gerenciador.repositories;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(
            value = "SELECT * FROM emprestimo "
                    + "WHERE emprestimo.id_usuario = :idUsuario "
                    + "AND emprestimo.id_livro = :idLivro "
                    + "AND (emprestimo.data_devolucao IS NULL "
                    + "OR emprestimo.pago = false AND emprestimo.multa > 0.0);",
        nativeQuery = true
    )
    Optional<Loan> findByUsuarioAndLivro(Long idUsuario, Long idLivro);

    @Query(
            value = "SELECT * FROM emprestimo "
                    + "WHERE emprestimo.data_devolucao > emprestimo.data_entrega_prevista "
                    + "AND emprestimo.id_usuario = :idUsuario "
                    + "AND emprestimo.pago = false"
                    + "OR emprestimo.data_devolucao IS NULL;",
            nativeQuery = true
    )
    Optional<Loan> findOverdueEmprestimo(@Param("idUsuario") Long idUsuario);

    @Query(
            value = "SELECT * FROM emprestimo "
                    + "WHERE emprestimo.multa > 0.0 "
                    + "AND emprestimo.id_usuario = :idUsuario "
                    + "AND emprestimo.pago = false;",
            nativeQuery = true
    )
    List<Loan> findNotPaidEmprestimo(@Param("idUsuario") Long idUsuario);

    @Query(
            value = "SELECT * FROM emprestimo "
                    + "WHERE emprestimo.id_usuario = :idUsuario "
                    + "AND emprestimo.data_devolucao IS NULL;",
            nativeQuery = true
    )
    List<Loan> findNotRefundEmprestimo(@Param("idUsuario") Long idUsuario);

}
