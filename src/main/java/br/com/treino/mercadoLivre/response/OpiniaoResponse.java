package br.com.treino.mercadoLivre.response;

public class OpiniaoResponse {
    private Integer nota;
    private String titulo;
    private String descricao;
    private ProdutoResponse produtoResponse;

    public OpiniaoResponse(Integer nota, String titulo,
                           String descricao, ProdutoResponse produtoResponse) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoResponse = produtoResponse;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoResponse getProdutoResponse() {
        return produtoResponse;
    }
}
