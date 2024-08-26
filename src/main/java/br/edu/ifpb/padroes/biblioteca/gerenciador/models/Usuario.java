package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UsuarioDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String cpf;
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    private String senha;
    @Column(name = "id_cargo")
    private Long cargo;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String cpf, String senha, Date dataNascimento, String endereco, Long cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cargo = cargo;
    }

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.cpf = usuarioDTO.cpf();
        this.senha = usuarioDTO.senha();
        this.dataNascimento = usuarioDTO.dataNascimento();
        this.endereco = usuarioDTO.endereco();
        this.cargo = usuarioDTO.cargo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }
}

