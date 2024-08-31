package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LivroDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Livro;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Livro> createNewLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertLivro(livroDTO));
    }
}
