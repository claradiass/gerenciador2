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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;

    public Book insertBook(BookRequestDTO bookRequestDTO) {
        if (findByIsbn(bookRequestDTO.isbn()) != null) {throw new AlreadyExistsException();}

        Book book = new Book();
        mapBookDetails(bookRequestDTO, book);

        return bookRepository.save(book);
    }

    public void updateQuantityBook(Long id, int quantity) {
        Book book = bookRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        book.setQuantity(quantity);
        bookRepository.save(book);
    }

    public Book updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = getBook(id);

        mapBookDetails(bookRequestDTO, book);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        getBook(id);
        bookRepository.deleteById(id);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(NotFoundException::new);
    }

    private void mapBookDetails(BookRequestDTO bookRequestDTO, Book book) {
        book.setIsbn(bookRequestDTO.isbn());
        book.setTitle(bookRequestDTO.titulo());
        book.setQuantity(bookRequestDTO.quantidade());
        book.setPublicationDate(bookRequestDTO.dataPublicacao());
        book.setSynopsis(bookRequestDTO.sinopse());

        Set<Author> authors = bookRequestDTO.autores().stream()
                .map(authorService::getAutorById)
                .collect(Collectors.toSet());

        Set<Genre> genres = bookRequestDTO.generos().stream()
                .map(genreService::getGenreById)
                .collect(Collectors.toSet());

        book.setAuthors(authors);
        book.setGenres(genres);
    }
}