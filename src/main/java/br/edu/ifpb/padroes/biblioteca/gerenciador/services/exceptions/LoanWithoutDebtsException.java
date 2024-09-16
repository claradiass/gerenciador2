package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class LoanWithoutDebtsException extends RuntimeException{
    public LoanWithoutDebtsException() {
        super("Empréstimo sem pendências.");
    }
}
