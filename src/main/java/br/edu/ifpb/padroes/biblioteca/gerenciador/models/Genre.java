package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.GenreRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genero")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "generos")
    private Set<Book> books;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }

    public Genre(GenreRequestDTO genreRequestDTO){
        this.name = genreRequestDTO.nome();
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
}

