package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.request.CaracteristicaRequest;
import br.com.treino.mercadoLivre.response.CaracteristicasResponse;
import br.com.treino.mercadoLivre.response.ImagemProdutoResponse;
import br.com.treino.mercadoLivre.response.ProdutoResponse;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.DETACH)
    private SubCategoria subCategoria;
    private String nomeProduto;
    private Integer quantidade;
    private String descricao;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagemProdutos = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    public Produto() {
    }

    public Produto(SubCategoria subCategoria, String nomeProduto,
                   Integer quantidade, String descricao, BigDecimal valor,
                   Usuario usuario,
                   Set<CaracteristicaRequest> caracteristicas) {
        this.subCategoria = subCategoria;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.caracteristicas = new CaracteristicaRequest()
                .converteToCaracteristicaEntity(caracteristicas);
    }

    @Override
    public String toString() {
        String devolvida = "Produto [nomeProduto=" + nomeProduto + " idProduto " + id +
                " quantidade " + quantidade + " descricao " + descricao +
                " valor " + valor + " caracteristicas : ";
        for (Caracteristica c : caracteristicas) {
            devolvida += c.toString();
        }
        for (ImagemProduto i : imagemProdutos) {
            devolvida += i.toString();
        }

        return devolvida;
    }

    public void associarImagens(List<String> links) {
        for (String link : links) {
            this.imagemProdutos.add(new ImagemProduto(link));
        }
    }

    public ProdutoResponse produtoResponse() {
        return new ProdutoResponse(subCategoria.subCategoriaResponse(),
                this.nomeProduto, this.quantidade, this.descricao, this.valor,
                usuario.usuarioResponse(), new CaracteristicasResponse().
                caracteristicasResponseSet(this.caracteristicas),
                new ImagemProdutoResponse().imagemProdutoResponses(this.imagemProdutos));
    }

}
