package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanUpdateRequestDTO(@NotNull LocalDate dataEntregaPrevista) {
}
