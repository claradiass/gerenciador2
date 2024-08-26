package br.edu.ifpb.padroes.biblioteca.gerenciador.models.livros;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroGeneroId implements Serializable {
    private Long livroId;
    private Long generoId;

    public LivroGeneroId() {
    }

    public LivroGeneroId(Long livroId, Long generoId) {
        this.livroId = livroId;
        this.generoId = generoId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroGeneroId that = (LivroGeneroId) o;
        return Objects.equals(livroId, that.livroId) && Objects.equals(generoId, that.generoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroId, generoId);
    }
}
