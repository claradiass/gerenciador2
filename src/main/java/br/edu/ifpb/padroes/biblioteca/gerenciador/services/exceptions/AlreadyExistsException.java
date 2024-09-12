package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super("Já existe.");
    }
}
