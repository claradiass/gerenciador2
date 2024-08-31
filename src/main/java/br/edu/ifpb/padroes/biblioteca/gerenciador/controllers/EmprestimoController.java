package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Emprestimo;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService service;

//    @PostMapping
//    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertEmprestimo(emprestimoDTO));
//    }

    @PostMapping
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){
        try {
            Emprestimo emprestimo = service.insertEmprestimo(emprestimoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimo);
        } catch (Exception e) {
            // Log exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable("id") Long id){
        var obj = service.getEmprestimoById(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Emprestimo> deleteEmprestimo(@PathVariable("id") Long id){
        var emprestimo = service.getEmprestimoById(id);
        service.deleteEmprestimo(id);
        return ResponseEntity.ok(emprestimo);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Emprestimo> updateEmprestimo(@PathVariable("id") Long id, @RequestBody EmprestimoDTO emprestimoDTO){
//        var updateEmprestimo = service.updateEmprestimo(id, emprestimoDTO);
//        return  ResponseEntity.ok(updateEmprestimo);
//
//    }
}
