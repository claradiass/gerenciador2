package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.util.Date;
import java.util.Set;

public record LivroDTO(String titulo, String isbn, String sinopse, int quantidade, Date dataPublicacao, Set<Long> autores, Set<Long> generos) {}
