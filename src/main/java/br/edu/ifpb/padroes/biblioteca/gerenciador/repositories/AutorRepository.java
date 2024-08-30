package br.edu.ifpb.padroes.biblioteca.gerenciador.repositories;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findAutorByNome(String nome);
}
