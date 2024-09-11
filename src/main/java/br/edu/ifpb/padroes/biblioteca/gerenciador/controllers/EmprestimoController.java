package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RequestPagamentoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.ResquestDevolucaoDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UpdateEmprestimoDTO;
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

    @PostMapping("/save")
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoDTO dto) {
        Emprestimo novoEmprestimo = service.insertEmprestimo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable("id") Long id){
        var obj = service.getEmprestimoById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Emprestimo> updateEmprestimo(@PathVariable("id") Long id, @RequestBody UpdateEmprestimoDTO emprestimoDTO){
        Emprestimo updateEmprestimo = service.updateEmprestimo(id, emprestimoDTO);
        return  ResponseEntity.ok(updateEmprestimo);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmprestimo(@PathVariable("id") Long id){
        service.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/devolucao/{id}")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable("id") Long id, @RequestBody ResquestDevolucaoDTO devolucaoDTO) {
        Emprestimo emprestimo = service.devolverLivro(id, devolucaoDTO.dataDevolucao());
        return ResponseEntity.ok(emprestimo);
    }

    @PutMapping("/pagamento/{id}")
    public ResponseEntity<Void> pagarMulta(@PathVariable("id") Long id, @RequestBody RequestPagamentoDTO pagamentoDTO) {
        service.pagarMulta(id, pagamentoDTO);
        return ResponseEntity.ok().build();
    }

}
