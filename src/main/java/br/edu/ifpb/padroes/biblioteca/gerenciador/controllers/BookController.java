package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.BookRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Book;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/create")
    public ResponseEntity<Book> createNewBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertBook(bookRequestDTO));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id){
        var obj = service.getBook(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks(){
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBook(id, bookRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
