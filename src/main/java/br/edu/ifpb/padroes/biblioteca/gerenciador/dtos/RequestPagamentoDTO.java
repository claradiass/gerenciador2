package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.util.Date;

public record RequestPagamentoDTO(Long usuarioId, Long livroId, Date dataDevolucao) { }
