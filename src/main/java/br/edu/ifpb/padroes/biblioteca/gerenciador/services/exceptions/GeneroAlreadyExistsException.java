package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class GeneroAlreadyExistsException extends RuntimeException{
    public GeneroAlreadyExistsException() {
        super("Não foi possivel criar um genero com esse nome.");
    }
}
