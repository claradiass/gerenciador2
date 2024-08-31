package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LivroDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Autor;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genero;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.AutorRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.GeneroRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.LivroRepository;
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
    private GeneroRepository repositoryGnero;

    public Livro insertLivro(LivroDTO livroDTO){

        Optional<Livro> livroOptional = findLivroByISBN(livroDTO.isbn());
        if(livroOptional.isEmpty()){
            Livro livro = new Livro();
            livro.setIsbn(livroDTO.isbn());
            livro.setTitulo(livroDTO.titulo());
            livro.setQuantidade(livroDTO.quantidade());
            livro.setDataPublicacao(livroDTO.dataPublicacao());
            livro.setSinopse(livroDTO.sinopse());

            // Converte o Set de IDs de autores em um Stream para processamento
            Set<Autor> autores = livroDTO.autores().stream()
                    // Mapeia cada ID de autor para um objeto Autor usando o método findAutorById
                    .map(this::findAutorById) // Método para buscar autor pelo ID
                    // Coleta os objetos Autor em um Set para garantir que não haja duplicatas
                    .collect(Collectors.toSet());

            // Define a lista de autores no objeto Livro usando o Set de autores obtidos
            livro.setAutores(autores);

            Set<Genero> generos = livroDTO.generos().stream()
                    .map(this::findGeneroById)
                    .collect(Collectors.toSet());
            livro.setGeneros(generos);

            return repositoryLivro.save(livro);

        }
        throw new RuntimeException("Livro já existente.");

    }

    public Optional<Livro> findLivroByISBN(String isbn){
        return repositoryLivro.findLivroByISBN(isbn);
    }

    private Autor findAutorById(Long id) {
        return repositoryAutor.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    // Método auxiliar para buscar gênero pelo ID
    private Genero findGeneroById(Long id) {
        return repositoryGnero.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com ID: " + id));
    }
}
