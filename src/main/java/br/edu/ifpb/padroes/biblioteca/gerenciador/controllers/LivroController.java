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

    @PostMapping("/save")
    public ResponseEntity<Livro> createNewLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertLivro(livroDTO));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable("id") Long id){
        var obj = service.getLivro(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Livro> editLivro(@PathVariable("id") Long id, @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateLivro(id, livroDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable("id") Long id) {
        service.deleteLivro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
