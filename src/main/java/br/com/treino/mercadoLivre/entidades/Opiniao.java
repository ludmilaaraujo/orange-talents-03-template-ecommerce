package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.response.OpiniaoResponse;

import javax.persistence.*;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nota;
    private String titulo;
    private String descricao;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Produto produto;

    public Opiniao(Integer nota, String titulo,
                   String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Opiniao() {
    }

    public OpiniaoResponse opiniaoResponse() {
        return new OpiniaoResponse(this.nota, this.titulo,
                this.descricao, produto.produtoResponse());
    }

    public Integer getNota() {
        return nota;
    }
}
