package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException() {
        super("Não foi possivel encontrar um usúario com esse id.");
    }
}
