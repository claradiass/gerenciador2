package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Role;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequestDTO(@NotNull String nome, @NotNull String endereco, @NotNull String cpf, @NotNull LocalDate dataNascimento, @NotNull String senha, @NotNull Role cargo) {}

