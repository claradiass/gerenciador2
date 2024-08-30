package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.livros.Livro;

import java.util.Date;

public record EmprestimoDTO(Usuario usuario, Livro livro, Date dataEmprestimo, Date dataEntregaPrevista, Date dataDevolucao, double multa, boolean pago) {}
