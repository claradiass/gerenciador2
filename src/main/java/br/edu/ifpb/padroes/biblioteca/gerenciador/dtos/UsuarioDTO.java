package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.UsuarioCargo;

import java.time.LocalDate;

public record UsuarioDTO(String nome, String endereco, String cpf, LocalDate dataNascimento, String senha, UsuarioCargo cargo) {}
