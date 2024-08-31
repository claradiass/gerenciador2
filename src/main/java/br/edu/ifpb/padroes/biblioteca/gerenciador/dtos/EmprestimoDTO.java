package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.util.Date;

public record EmprestimoDTO(Long usuarioId, Long livroId, Date dataEmprestimo, Date dataEntregaPrevista, Date dataDevolucao, double multa, boolean pago) {}

