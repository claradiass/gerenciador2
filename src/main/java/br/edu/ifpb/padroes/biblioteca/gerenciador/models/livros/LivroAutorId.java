package br.edu.ifpb.padroes.biblioteca.gerenciador.models.livros;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LivroAutorId implements Serializable {
    private Long livroId;
    private Long autorId;

    public LivroAutorId() {
    }

    public LivroAutorId(Long livroId, Long autorId) {
        this.livroId = livroId;
        this.autorId = autorId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroAutorId that = (LivroAutorId) o;
        return Objects.equals(livroId, that.livroId) && Objects.equals(autorId, that.autorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroId, autorId);
    }
}
