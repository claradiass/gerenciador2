package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.GeneroDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genero;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.GeneroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository repository;

    public Genero insertGenero(GeneroDTO generoDTO){
        var genero = repository.findGeneroByNome(generoDTO.nome());
        if (genero == null) {
            return repository.save(new Genero(generoDTO));
        }
        throw new AlreadyExistsException();
    }

    public Genero getGeneroById(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Genero updateGenero(Long id, GeneroDTO generoDTO){
        var genero = getGeneroById(id);

        genero.setNome(generoDTO.nome());
        return repository.save(genero);
    }

    public void deleteGenero(Long id){
        Genero genero = getGeneroById(id);
        repository.delete(genero);
    }
}
