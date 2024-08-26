package br.edu.ifpb.padroes.biblioteca.gerenciador.models.livros;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Genero;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "livro_genero")
public class LivroGenero implements Serializable {

    @EmbeddedId
    private LivroGeneroId id;

//    @ManyToOne
//    @MapsId("livroId")
//    @JoinColumn(name = "livro_id")
    @Transient
    private Livro livro;

    @ManyToOne
    @MapsId("generoId")
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public LivroGenero() {
    }

    public LivroGenero(Livro livro, Genero genero) {
        this.livro = livro;
        this.genero = genero;
        this.id = new LivroGeneroId(livro.getId(), genero.getId());
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
