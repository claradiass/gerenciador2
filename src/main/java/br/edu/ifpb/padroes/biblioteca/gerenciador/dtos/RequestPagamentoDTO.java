package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.time.LocalDate;

public record RequestPagamentoDTO(Long emprestimoId, LocalDate dataDevolucao) { }
