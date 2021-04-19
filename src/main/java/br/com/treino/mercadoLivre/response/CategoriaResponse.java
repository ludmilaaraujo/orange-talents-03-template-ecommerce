package br.com.treino.mercadoLivre.response;

public class CategoriaResponse {
    private String nomeCategoria;

    @Deprecated
    public CategoriaResponse() {
    }

    public CategoriaResponse(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
