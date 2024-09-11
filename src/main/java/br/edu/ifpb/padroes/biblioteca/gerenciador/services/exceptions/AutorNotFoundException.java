package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class AutorNotFoundException extends RuntimeException{
    public AutorNotFoundException() {
        super("Não foi possivel encontrar um autor com esse id.");
    }
}
