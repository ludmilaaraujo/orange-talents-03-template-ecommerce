package br.com.treino.mercadoLivre.response;

public class PerguntaResponse {

    private String pergunta;
    private UsuarioResponse usuario;
    private ProdutoResponse produto;

    public PerguntaResponse(String pergunta, UsuarioResponse usuario, ProdutoResponse produto) {
        this.pergunta = pergunta;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getPergunta() {
        return pergunta;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }
}
