package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class AutorAlreadyExistsException extends RuntimeException{
    public AutorAlreadyExistsException() {
        super("Não foi possivel criar um autor com esse nome.");
    }
}
