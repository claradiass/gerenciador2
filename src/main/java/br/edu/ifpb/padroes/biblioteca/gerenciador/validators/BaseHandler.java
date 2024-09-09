package br.edu.ifpb.padroes.biblioteca.gerenciador.validators;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;

public abstract class BaseHandler {
    private BaseHandler next;

    public static BaseHandler link(BaseHandler ... chain) {
        if (chain == null || chain.length == 0) {
            throw new IllegalArgumentException("A cadeia de handlers não pode ser vazia");
        }

        BaseHandler head = chain[0];
        BaseHandler current = head;

        for (int i = 1; i < chain.length; i++) {
            current.setNext(chain[i]);
            current = chain[i];
        }
        return head;
    }

    public abstract void check(EmprestimoDTO data);

    protected void checkNext(EmprestimoDTO data) {
        if (next != null) {
            next.check(data);
        }
    }

    public void setNext(BaseHandler next) {
        this.next = next;
    }
}
