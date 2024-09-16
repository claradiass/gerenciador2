package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class BookHasAlreadyBeenReturnedException extends RuntimeException{
    public BookHasAlreadyBeenReturnedException() {
        super("O livro já foi devolvido.");
    }
}
