package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class LoanAlreadyPaidException extends RuntimeException{
    public LoanAlreadyPaidException() {
        super("Este emprestimo já foi pago.");
    }
}
