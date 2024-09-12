package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.PaymentRequestDTO;
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
        Loan newLoan = service.insertEmprestimo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLoan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable("id") Long id){
        var obj = service.getEmprestimoById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") Long id, @RequestBody LoanUpdateRequestDTO loanDTO){
        Loan updateLoan = service.updateEmprestimo(id, loanDTO);
        return  ResponseEntity.ok(updateLoan);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable("id") Long id){
        service.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/refund/{id}")
    public ResponseEntity<Loan> devolverLivro(@PathVariable("id") Long id, @RequestBody RefundResquestDTO refundDTO) {
        Loan loan = service.devolverLivro(id, refundDTO.dataDevolucao());
        return ResponseEntity.ok(loan);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<Void> pagarMulta(@PathVariable("id") Long id, @RequestBody PaymentRequestDTO paymentDTO) {
        service.pagarMulta(id, paymentDTO);
        return ResponseEntity.ok().build();
    }

}
