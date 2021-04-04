package br.com.treino.mercadoLivre.entidades;

import javax.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    private SubCategoria subCategoria;
    private String nomeProduto;

    public Produto(SubCategoria subCategoria, String nomeProduto) {
        this.subCategoria = subCategoria;
        this.nomeProduto = nomeProduto;
    }

    @Override
    public  String toString(){
        return "Produto [nomeProduto=" + nomeProduto + "]";
    }
}
