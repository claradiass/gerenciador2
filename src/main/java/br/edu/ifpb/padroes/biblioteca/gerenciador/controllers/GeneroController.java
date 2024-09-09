package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.GeneroDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genero;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService service;

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody GeneroDTO generoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertGenero(generoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> getGenero(@PathVariable("id") Long id){
        var obj = service.getGeneroById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Genero> updateGenero(@PathVariable("id") Long id, @RequestBody GeneroDTO generoDTO){
        Genero updateGenero = service.updateGenero(id, generoDTO);
        return ResponseEntity.ok(updateGenero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genero> deleteGenero(@PathVariable("id") Long id){
        service.getGeneroById(id);
        service.deleteGenero(id);
        GregorianCalendar.getInstance();
        return ResponseEntity.noContent().build();
    }

}
