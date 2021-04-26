package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.entidades.StatusPagamento;

public class PosVendaRequest {

    private Long idCompra;
    private StatusPagamento statusPagamento;

    public PosVendaRequest(Long idCompra, StatusPagamento statusPagamento) {
        this.idCompra = idCompra;
        this.statusPagamento = statusPagamento;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

}
