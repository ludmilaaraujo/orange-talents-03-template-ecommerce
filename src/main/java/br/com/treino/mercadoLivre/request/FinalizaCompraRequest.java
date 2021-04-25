package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FinalizaCompraRequest {

    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public FinalizaCompraRequest(@Positive int quantidade,
                                 @NotNull Long idProduto,
                                 @NotNull GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gatewayPagamento = gatewayPagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Produto devolverProdutoBanco(ProdutoRepository produtoRepository){
        return produtoRepository.getOne(idProduto);
    }
}
