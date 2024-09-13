package br.edu.ifpb.padroes.biblioteca.gerenciador.validators;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;

public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return this.nextHandler;
    }

    public abstract void check(LoanRequestDTO data);

}
