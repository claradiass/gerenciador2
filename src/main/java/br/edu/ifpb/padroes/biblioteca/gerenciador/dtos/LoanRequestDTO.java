package br.edu.ifpb.padroes.biblioteca.gerenciador.dtos;

import java.time.LocalDate;

public record LoanRequestDTO(Long usuarioId, Long livroId, LocalDate dataEmprestimo, LocalDate dataEntregaPrevista) {}
