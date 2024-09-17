package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(@NotNull String cpf, @NotNull String senha) {
}
