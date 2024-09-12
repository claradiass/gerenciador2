package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.RefundResquestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanUpdateRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Loan;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/emprestimo")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/create")
    public ResponseEntity<Loan> createNewLoan(@RequestBody LoanRequestDTO dto) {
        Loan newLoan = service.insertLoan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLoan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable("id") Long id){
        var obj = service.getLoanById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") Long id, @RequestBody LoanUpdateRequestDTO loanDTO){
        Loan updateLoan = service.updateLoan(id, loanDTO);
        return  ResponseEntity.ok(updateLoan);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") Long id){
        service.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/refund/{id}")
    public ResponseEntity<Loan> refundBook(@PathVariable("id") Long id, @RequestBody RefundResquestDTO refundDTO) {
        Loan loan = service.refundBook(id, refundDTO.dataDevolucao());
        return ResponseEntity.ok(loan);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<Void> payLateFee(@PathVariable("id") Long id) {
        service.payLateFee(id);
        return ResponseEntity.ok().build();
    }

}
