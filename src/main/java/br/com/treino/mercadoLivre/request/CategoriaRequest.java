package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.UniqueValue;
import br.com.treino.mercadoLivre.entidades.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nomeCategoria;

    public CategoriaRequest(@NotBlank String nome) {
        this.nomeCategoria = nome;
    }

    public CategoriaRequest() {}

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Categoria converteToEntity() {
        return new Categoria(this.nomeCategoria);
    }
}
