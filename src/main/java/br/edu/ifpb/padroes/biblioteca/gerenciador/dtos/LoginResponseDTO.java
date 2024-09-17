package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import jakarta.validation.constraints.NotNull;

public record LoginResponseDTO(@NotNull String token) {
}
