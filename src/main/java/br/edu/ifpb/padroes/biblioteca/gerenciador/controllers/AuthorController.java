package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RequestAuthorDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Autor;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AutorService service;

    @PostMapping
    public ResponseEntity<Autor> createNewAuthor(@RequestBody RequestAuthorDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertAuthor(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAuthor(@PathVariable("id") Long id, @RequestBody RequestAuthorDTO data) {
        return ResponseEntity.ok(service.updateAuthor(id, data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAuthors(@PathVariable("id") Long id) {
        var obj = service.getAutorById(id);
        return ResponseEntity.ok(obj);
    }
}
