package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.AuthorRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "autor")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(AuthorRequestDTO data) {
        this.name = data.nome();
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

    public Set<Book> getLivros() {
        return books;
    }

    public void setLivros(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

