package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.time.LocalDate;

public record LoanUpdateRequestDTO(LocalDate dataEntregaPrevista) {
}
