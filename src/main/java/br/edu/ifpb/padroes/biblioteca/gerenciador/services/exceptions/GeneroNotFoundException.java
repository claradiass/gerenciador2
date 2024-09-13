package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class GeneroNotFoundException extends RuntimeException{
    public GeneroNotFoundException() {
        super("Não foi possivel encontrar um genero com esse id.");
    }
}
