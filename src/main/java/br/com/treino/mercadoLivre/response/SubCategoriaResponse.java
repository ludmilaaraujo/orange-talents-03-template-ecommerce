package br.com.treino.mercadoLivre.response;

public class SubCategoriaResponse {

    private CategoriaResponse categoria;
    private String nomeSubCategoria;

    @Deprecated
    public SubCategoriaResponse() {
    }

    public SubCategoriaResponse(CategoriaResponse categoria, String nomeSubCategoria) {
        this.categoria = categoria;
        this.nomeSubCategoria = nomeSubCategoria;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public String getNomeSubCategoria() {
        return nomeSubCategoria;
    }
}
