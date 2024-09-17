package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.AuthorRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Author;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping("/create")
    public ResponseEntity<Author> createNewAuthor(@Valid @RequestBody AuthorRequestDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertAuthor(authorDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Long id, @Valid @RequestBody AuthorRequestDTO authorDTO) {
        return ResponseEntity.ok(service.updateAuthor(id, authorDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthors(@PathVariable("id") Long id) {
        var obj = service.getAutorById(id);
        return ResponseEntity.ok(obj);
    }
}
