package br.edu.ifpb.padroes.biblioteca.gerenciador.repositories;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query(
            value = "SELECT emprestimo.*, usuario.id as usuario_id, livro.id as livro_id FROM emprestimo "
                    + "INNER JOIN usuario ON emprestimo.id_usuario = usuario.id "
                    + "INNER JOIN livro ON emprestimo.id_livro = livro.id "
                    + "WHERE usuario.id = :idUsuario "
                    + "AND livro.id = :idLivro",
            nativeQuery = true
    )
    Optional<Emprestimo> findByUsuarioAndLivro(Long idUsuario, Long idLivro);

    @Query(
            value = "SELECT emprestimo.*, usuario.id as usuario_id FROM emprestimo "
                    + "INNER JOIN usuario ON emprestimo.id_usuario = usuario.id "
                    + "WHERE emprestimo.data_devolucao > emprestimo.data_entrega_prevista "
                    + "AND usuario.id = :idUsuario "
                    + "AND emprestimo.pago = false",
            nativeQuery = true
    )
    Optional<Emprestimo> findOverdueEmprestimo(@Param("idUsuario") Long idUsuario);

    @Query(
            value = "SELECT * FROM emprestimo "
                    + "WHERE emprestimo.id_usuario = :idUsuario "
                    + "AND emprestimo.pago = false",
            nativeQuery = true
    )
    List<Emprestimo> findNotPaidEmprestimoByUsuarioId(@Param("idUsuario") Long idUsuario);

    List<Emprestimo> findByUsuarioId(Long id);

    List<Emprestimo> findByLivroId(Long id);

}
