package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public record BookRequestDTO(@NotNull String titulo, @NotNull String isbn, @NotNull String sinopse, @NotNull int quantidade, @NotNull LocalDate dataPublicacao, @NotNull Set<Long> autores, @NotNull Set<Long> generos) {}
