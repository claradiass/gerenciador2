package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthorRequestDTO(@NotNull String nome) {

}
