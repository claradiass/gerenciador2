package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Não foi possivel encontrar com esse id.");
    }
}
