package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.GenreRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genre;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;

@RestController
@RequestMapping("/genero")
public class GenreController {

    @Autowired
    private GenreService service;

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestBody GenreRequestDTO genreRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertGenre(genreRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable("id") Long id){
        var obj = service.getGenreById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Genre> updateGenre(@PathVariable("id") Long id, @RequestBody GenreRequestDTO genreRequestDTO){
        Genre updateGenero = service.updateGenre(id, genreRequestDTO);
        return ResponseEntity.ok(updateGenero);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable("id") Long id){
        service.deleteGenre(id);
        GregorianCalendar.getInstance();
        return ResponseEntity.noContent().build();
    }
}
