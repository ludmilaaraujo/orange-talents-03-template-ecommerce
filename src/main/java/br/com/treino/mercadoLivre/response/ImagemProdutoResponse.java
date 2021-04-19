package br.com.treino.mercadoLivre.response;

import br.com.treino.mercadoLivre.entidades.ImagemProduto;

import java.util.HashSet;
import java.util.Set;

public class ImagemProdutoResponse {

    private String link;

    public ImagemProdutoResponse(String link) {
        this.link = link;
    }

    public ImagemProdutoResponse() {
    }

    public Set<ImagemProdutoResponse> imagemProdutoResponses(
            Set<ImagemProduto> imagemProdutoSet) {
        Set<ImagemProdutoResponse> setDevolvido = new HashSet<ImagemProdutoResponse>();
        for (ImagemProduto imagemProduto : imagemProdutoSet) {
            setDevolvido.add(imagemProduto.imagemProdutoResponse());
        }
        return setDevolvido;
    }

    public String getLink() {
        return link;
    }
}
