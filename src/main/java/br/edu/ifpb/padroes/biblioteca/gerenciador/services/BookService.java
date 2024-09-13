package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.BookRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Author;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genre;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.AuthorRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.GenreRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.BookRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.AlreadyExistsException;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    public Book insertBook(BookRequestDTO bookRequestDTO){

        Optional<Book> newBook = findByIsbn(bookRequestDTO.isbn());

        if (newBook.isEmpty()){
            Book book = new Book();
            book.setIsbn(bookRequestDTO.isbn());
            book.setTitle(bookRequestDTO.titulo());
            book.setQuantity(bookRequestDTO.quantidade());
            book.setPublicationDate(bookRequestDTO.dataPublicacao());
            book.setSynopsis(bookRequestDTO.sinopse());

            Set<Author> authors = bookRequestDTO.autores().stream()
                    .map(this::findAutorById)
                    .collect(Collectors.toSet());

            book.setAuthors(authors);

            Set<Genre> genres = bookRequestDTO.generos().stream()
                    .map(this::findGeneroById)
                    .collect(Collectors.toSet());

            book.setGenres(genres);

            return bookRepository.save(book);
        }

        throw new AlreadyExistsException();
    }

    public void updateQuantityBook(Long id, int quantity){
        Book book = bookRepository.findById(id).orElseThrow();

        book.setQuantity(quantity);
    }

    public Book updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id).orElseThrow();

        book.setIsbn(bookRequestDTO.isbn());
        book.setTitle(bookRequestDTO.titulo());
        book.setQuantity(bookRequestDTO.quantidade());
        book.setPublicationDate(bookRequestDTO.dataPublicacao());
        book.setSynopsis(bookRequestDTO.sinopse());

        Set<Author> autores = bookRequestDTO.autores().stream()
                .map(this::findAutorById)
                .collect(Collectors.toSet());

        book.setAuthors(autores);

        Set<Genre> generos = bookRequestDTO.generos().stream()
                .map(this::findGeneroById)
                .collect(Collectors.toSet());

        book.setGenres(generos);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            return book.get();
        }

        throw new NotFoundException();
    }

    public Optional<Book> findByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    private Author findAutorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    private Genre findGeneroById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com ID: " + id));
    }
}
