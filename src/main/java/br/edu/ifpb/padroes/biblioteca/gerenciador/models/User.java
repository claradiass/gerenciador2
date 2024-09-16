package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UserRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;

    @Column(name = "endereco")
    private String address;

    @Column(name = "cpf",unique = true)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate birthDate;

    @Column(name = "senha")
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo")
    private Role role;

    public User() {
    }

    public User(Long id, String name, String cpf, String password, LocalDate birthDate, String address, Role role) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
    }

    public User(String name, String cpf, String password, LocalDate birthDate, String address, Role role) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
    }

    public User(UserRequestDTO userRequestDTO) {
        this.name = userRequestDTO.nome();
        this.cpf = userRequestDTO.cpf();
        this.password = userRequestDTO.senha();
        this.birthDate = userRequestDTO.dataNascimento();
        this.address = userRequestDTO.endereco();
        this.role = userRequestDTO.cargo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

