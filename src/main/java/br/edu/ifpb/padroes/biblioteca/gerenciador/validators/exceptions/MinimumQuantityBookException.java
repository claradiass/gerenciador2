package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.exceptions;

public class MinimumQuantityBookException extends RuntimeException {
    public MinimumQuantityBookException() { super("Quantidade de livro insuficiente"); }
}
