package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record ResquestDevolucaoDTO(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataDevolucao
    ) {}

