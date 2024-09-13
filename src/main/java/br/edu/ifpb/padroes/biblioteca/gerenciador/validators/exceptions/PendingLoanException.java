package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions;

public class PendingLoanException extends RuntimeException {
    public PendingLoanException() {
        super("Você precisa devolver os empréstimos pendentes para realizar novos empréstimos.");
    }
}
