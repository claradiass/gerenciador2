package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Role;

import java.time.LocalDate;

public record UserRequestDTO(String nome, String endereco, String cpf, LocalDate dataNascimento, String senha, Role cargo) {}

