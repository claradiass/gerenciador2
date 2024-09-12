package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions;

public class LoanLimitException extends RuntimeException {
    public LoanLimitException() { super("Você possui 3 livros não devolvidos ainda."); }
}
