package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.AuthorRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Author;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.AuthorRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public Author insertAuthor(AuthorRequestDTO authorDTO) {
        var author = repository.findAutorByNome(authorDTO.nome());

        if (author != null) { throw new AlreadyExistsException(); }

        return repository.save(new Author(authorDTO));
    }

    public Author getAutorById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Author updateAuthor(Long id, AuthorRequestDTO data) {
        var author = getAutorById(id);

        author.setName(data.nome());
        return repository.save(author);
    }

}
