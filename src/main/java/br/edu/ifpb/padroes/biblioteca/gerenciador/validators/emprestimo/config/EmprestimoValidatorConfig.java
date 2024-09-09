package br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.config;

import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.EmprestimoValidatorChain;
import br.edu.ifpb.padroes.biblioteca.gerenciador.validators.emprestimo.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmprestimoValidatorConfig {

    @Bean
    public EmprestimoValidatorChain emprestimoValidatorChain(UsuarioExiste usuarioExiste, LivroExiste livroExiste,UsuarioJaPossuiEmprestimoDoLivro usuarioJaPossuiEmprestimoDoLivro, PagamentoEmprestimoAtrasado pagamentoEmprestimoAtrasado, UsuarioExcedeuLimiteEmprestimos usuarioExcedeuLimiteEmprestimos, QuantidadeLivrosInsuficiente quantidadeLivrosInsuficiente) {
        return new EmprestimoValidatorChain(usuarioExiste, livroExiste, usuarioJaPossuiEmprestimoDoLivro, pagamentoEmprestimoAtrasado, usuarioExcedeuLimiteEmprestimos, quantidadeLivrosInsuficiente);
    }
}
