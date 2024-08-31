package br.edu.ifpb.padroes.biblioteca.gerenciador.repositories;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findLivroByISBN(String isbn);
}
