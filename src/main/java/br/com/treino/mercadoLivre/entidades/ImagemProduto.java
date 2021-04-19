package br.com.treino.mercadoLivre.entidades;


import br.com.treino.mercadoLivre.response.ImagemProdutoResponse;

import javax.persistence.*;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

    public ImagemProduto() {
    }

    public ImagemProduto(String link) {

        this.link = link;
    }

    @Override
    public String toString(){

        return "Link da Imagem: " + imagemProdutoResponse();
    }

    public ImagemProdutoResponse imagemProdutoResponse(){

        return new ImagemProdutoResponse(this.link);
    }
}
