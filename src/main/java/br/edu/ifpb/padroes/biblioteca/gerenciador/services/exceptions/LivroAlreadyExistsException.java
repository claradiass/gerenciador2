package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class LivroAlreadyExistsException extends RuntimeException{
    public LivroAlreadyExistsException() {
        super("Já existe um livro com essa ISBN.");
    }
}
