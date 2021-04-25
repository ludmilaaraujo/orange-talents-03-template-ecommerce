package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Valid @ManyToOne
    private Produto produtoQueSeraComprado;
    @Positive
    private Integer quantidade;
    @NotNull @Valid @ManyToOne
    private Usuario usuario;
    @Enumerated @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra(@NotNull @Valid Produto produtoQueSeraComprado,
                  @Positive Integer quantidade, @NotNull @Valid Usuario usuario,
                  @NotNull GatewayPagamento gatewayPagamento) {
        this.produtoQueSeraComprado = produtoQueSeraComprado;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gatewayPagamento = gatewayPagamento;
    }

    public String getEmailVendedor(){
        return usuario.getLogin();
    }

}