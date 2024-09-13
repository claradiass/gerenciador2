package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class LivroNotFoundException extends RuntimeException{
    public LivroNotFoundException() {
        super("Não foi possivel encontrar o livro com esse id.");
    }
}
