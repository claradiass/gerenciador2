package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ResquestDevolucaoDTO(LocalDate dataDevolucao) {}

