package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.GenreRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genre;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.GenreRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;

    public Genre insertGenre(GenreRequestDTO genreRequestDTO){
        var genre = repository.findGenreByName(genreRequestDTO.nome());

        if (genre == null) {
            return repository.save(new Genre(genreRequestDTO));
        }

        throw new AlreadyExistsException();
    }

    public Genre getGenreById(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Genre updateGenre(Long id, GenreRequestDTO genreRequestDTO){
        var genre = getGenreById(id);

        genre.setName(genreRequestDTO.nome());
        return repository.save(genre);
    }

    public void deleteGenre(Long id){
        Genre genre = getGenreById(id);
        repository.delete(genre);
    }
}
