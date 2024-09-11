package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LivroDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Autor;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genero;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.AutorRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.GeneroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repositoryLivro;
    @Autowired
    private AutorRepository repositoryAutor;
    @Autowired
    private GeneroRepository repositoryGenero;

    public Livro insertLivro(LivroDTO livroDTO){

        Optional<Livro> livroOptional = findByIsbn(livroDTO.isbn());

        if (livroOptional.isEmpty()){
            Livro livro = new Livro();
            livro.setIsbn(livroDTO.isbn());
            livro.setTitulo(livroDTO.titulo());
            livro.setQuantidade(livroDTO.quantidade());
            livro.setDataPublicacao(livroDTO.dataPublicacao());
            livro.setSinopse(livroDTO.sinopse());

            Set<Autor> autores = livroDTO.autores().stream()
                    .map(this::findAutorById)
                    .collect(Collectors.toSet());

            livro.setAutores(autores);

            Set<Genero> generos = livroDTO.generos().stream()
                    .map(this::findGeneroById)
                    .collect(Collectors.toSet());

            livro.setGeneros(generos);

            return repositoryLivro.save(livro);

        }

        throw new AlreadyExistsException();
    }

    public void updateQuantidadeLivro(Long id, int quantidade){
        Livro livro = repositoryLivro.findById(id).orElseThrow();

        livro.setQuantidade(quantidade);
    }

    public Livro updateLivro(Long id, LivroDTO livroDTO) {
        Livro livro = repositoryLivro.findById(id).orElseThrow();

        livro.setIsbn(livroDTO.isbn());
        livro.setTitulo(livroDTO.titulo());
        livro.setQuantidade(livroDTO.quantidade());
        livro.setDataPublicacao(livroDTO.dataPublicacao());
        livro.setSinopse(livroDTO.sinopse());

        Set<Autor> autores = livroDTO.autores().stream()
                .map(this::findAutorById)
                .collect(Collectors.toSet());

        livro.setAutores(autores);

        Set<Genero> generos = livroDTO.generos().stream()
                .map(this::findGeneroById)
                .collect(Collectors.toSet());

        livro.setGeneros(generos);

        return repositoryLivro.save(livro);
    }

    public void deleteLivro(Long id) {
        repositoryLivro.deleteById(id);
    }

    public Livro getLivro(Long id){
        Optional<Livro> livroOptional = repositoryLivro.findById(id);
        if(livroOptional.isPresent()){
            return livroOptional.get();
        }
        throw new NotFoundException();
    }

    public Optional<Livro> findByIsbn(String isbn){
        return repositoryLivro.findByIsbn(isbn);
    }

    private Autor findAutorById(Long id) {
        return repositoryAutor.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    private Genero findGeneroById(Long id) {
        return repositoryGenero.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com ID: " + id));
    }
}
