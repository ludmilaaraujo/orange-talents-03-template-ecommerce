package br.com.treino.mercadoLivre.response;

import br.com.treino.mercadoLivre.request.CaracteristicaRequest;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoResponse {

    private SubCategoriaResponse subCategoriaResponse;
    private String nomeProduto;
    private int quantidade;
    private String descricao;
    private BigDecimal valor;
    private UsuarioResponse usuarioResponse;
    private Set<CaracteristicasResponse> caracteristicas;
    private Set<ImagemProdutoResponse> imagemProdutoResponses;

    public ProdutoResponse(SubCategoriaResponse subCategoriaResponse, String nomeProduto,
                           int quantidade, String descricao, BigDecimal valor,
                           UsuarioResponse usuarioResponse,
                           Set<CaracteristicasResponse> caracteristicas,
                           Set<ImagemProdutoResponse> imagemProdutoResponses) {
        this.subCategoriaResponse = subCategoriaResponse;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.usuarioResponse = usuarioResponse;
        this.caracteristicas = caracteristicas;
        this.imagemProdutoResponses = imagemProdutoResponses;
    }

    public ProdutoResponse() {
    }

    public SubCategoriaResponse getSubCategoriaResponse() {
        return subCategoriaResponse;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }

    public Set<CaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProdutoResponse> getImagemProdutoResponses() {
        return imagemProdutoResponses;
    }
}
