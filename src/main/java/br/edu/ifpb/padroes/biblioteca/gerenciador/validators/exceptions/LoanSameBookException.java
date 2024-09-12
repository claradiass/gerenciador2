package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions;

public class LoanSameBookException extends RuntimeException {
    public LoanSameBookException() { super("Você já possui um empréstimo desse mesmo livro."); }
}
