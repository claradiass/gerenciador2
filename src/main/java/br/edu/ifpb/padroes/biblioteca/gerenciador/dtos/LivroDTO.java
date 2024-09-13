package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.time.LocalDate;
import java.util.Set;

public record LivroDTO(String titulo, String isbn, String sinopse, int quantidade, LocalDate dataPublicacao, Set<Long> autores, Set<Long> generos) {}
