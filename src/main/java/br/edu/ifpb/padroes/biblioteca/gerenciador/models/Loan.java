package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoanRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Book book;

    @Column(name = "data_emprestimo")
    private LocalDate loanDate;

    @Column(name = "data_entrega_prevista")
    private LocalDate estimatedDate;

    @Column(name = "data_devolucao")
    private LocalDate refundDate;

    @Column(name = "multa")
    private double fee;

    @Column(name = "pago")
    private boolean paid;

    public Loan(Long id, User user, Book book, LocalDate loanDate, LocalDate estimatedDate, LocalDate refundDate, double fee, boolean paid) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.estimatedDate = estimatedDate;
        this.refundDate = refundDate;
        this.fee = fee;
        this.paid = paid;
    }

    public Loan() {
    }

    public Loan(LoanRequestDTO dto, User user, Book book) {
        this.user = user;
        this.book = book;
        this.loanDate = dto.dataEmprestimo();
        this.estimatedDate = dto.dataEntregaPrevista();
        this.refundDate = null;
        this.fee = 0;
        this.paid = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

