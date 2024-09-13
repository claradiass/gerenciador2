package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions;

public class NotPaidException extends RuntimeException {
    public NotPaidException() { super("O usuário tem multas pendentes."); }
}
