package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.util.Date;

public record UsuarioDTO(String nome, String endereco, String cpf, Date dataNascimento, String senha, Long cargo) {}
