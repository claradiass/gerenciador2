package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanRequestDTO(@NotNull Long usuarioId, @NotNull Long livroId, @NotNull LocalDate dataEmprestimo, @NotNull LocalDate dataEntregaPrevista) {}
