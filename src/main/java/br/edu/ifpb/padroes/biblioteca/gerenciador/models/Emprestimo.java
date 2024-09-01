package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.EmprestimoDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;


    @Column(name = "data_emprestimo")
    private Date dataEmprestimo;
    @Column(name = "data_entrega_prevista")
    private Date dataEntregaPrevista;
    @Column(name = "data_devolucao")
    private Date dataDevolucao;
    private double multa;
    private boolean pago;

    public Emprestimo(Long id, Usuario usuario, Livro livro, Date dataEmprestimo, Date dataEntregaPrevista, Date dataDevolucao, double multa, boolean pago) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataEntregaPrevista = dataEntregaPrevista;
        this.dataDevolucao = dataDevolucao;
        this.multa = multa;
        this.pago = pago;
    }

    public Emprestimo() {
    }

    public Emprestimo(EmprestimoDTO dto, Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dto.dataEmprestimo();
        this.dataEntregaPrevista = dto.dataEntregaPrevista();
        this.dataDevolucao = dto.dataDevolucao();
        this.multa = dto.multa();
        this.pago = dto.pago();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataEntregaPrevista() {
        return dataEntregaPrevista;
    }

    public void setDataEntregaPrevista(Date dataEntregaPrevista) {
        this.dataEntregaPrevista = dataEntregaPrevista;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}

