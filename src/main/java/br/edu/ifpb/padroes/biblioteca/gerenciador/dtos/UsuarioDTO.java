package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.UsuarioCargo;

import java.util.Date;

public record UsuarioDTO(String nome, String endereco, String cpf, Date dataNascimento, String senha, UsuarioCargo cargo) {}
