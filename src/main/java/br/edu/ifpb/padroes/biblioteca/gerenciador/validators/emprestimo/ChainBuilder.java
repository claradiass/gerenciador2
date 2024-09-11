package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo;

import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.Handler;
import org.springframework.stereotype.Component;

@Component
public class ChainBuilder {
    private Handler firstHandler;

    public ChainBuilder addHandler(Handler handler) {
        if (this.firstHandler == null) {
            this.firstHandler = handler;
        } else {
            Handler current = this.firstHandler;
            while (current.getNextHandler() != null) {
                current = current.getNextHandler();
            }
            current.setNextHandler(handler);
        }
        return this;
    }

    public Handler build() {
        return this.firstHandler;
    }
}
