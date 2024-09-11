package br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions;

public class EmprestimoNotFoundException extends RuntimeException{
    public EmprestimoNotFoundException() {
        super("Não foi possivel encontrar um emprestimo com esse id.");
    }
}
