package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RequestAuthorDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Autor;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.AutorRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AutorAlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AutorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    public Autor insertAuthor(RequestAuthorDTO data) {
        var author = findAuthorByName(data.nome());
        if (author == null) {
            return repository.save(new Autor(data));
        }
        throw new AutorAlreadyExistsException();
    }

    public Autor getAutorById(Long id) {
        return repository.findById(id)
                .orElseThrow(AutorNotFoundException::new);
    }

    public Autor updateAuthor(Long id, RequestAuthorDTO data) {
        var author = getAutorById(id);

        author.setNome(data.nome());
        return repository.save(author);
    }

    private Autor findAuthorByName(String name) {
        return repository.findAutorByNome(name);
    }
}
