package br.com.treino.mercadoLivre.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Categoria categoria;
    @OneToMany(mappedBy="subCategoria")
    private List<Produto> produtos;
    private String nomeSubCategoria;

    @Deprecated
    public SubCategoria() {

    }

    public SubCategoria(Categoria categoria, String nomeSubCategoria) {
        this.categoria = categoria;
        this.produtos = produtos;
        this.nomeSubCategoria = nomeSubCategoria;
    }


    @Override
    public  String toString(){
        return "Autor [nomeSubCategoria=" + nomeSubCategoria + "]";
    }

}
