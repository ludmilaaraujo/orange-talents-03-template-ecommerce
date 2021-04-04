package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.ExisteEntidade;
import br.com.treino.mercadoLivre.anotacoes.UniqueValue;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;

import javax.validation.constraints.NotNull;

public class ProdutoRequest {
    @NotNull
    @ExisteEntidade(domainClass = SubCategoria.class, fieldName = "id")
    private Long idSubCategoria;
    @UniqueValue(domainClass = Produto.class, fieldName = "nomeProduto")
    private String nomeProduto;

    public ProdutoRequest(@NotNull Long idSubCategoria, String nomeProduto) {
        this.idSubCategoria = idSubCategoria;
        this.nomeProduto = nomeProduto;
    }

    public Produto converteToEntity(SubCategoriaRepository subCategoriaRepository) {
        SubCategoria subCategoria
                = subCategoriaRepository.getOne(idSubCategoria);
        return new Produto(subCategoria,this.nomeProduto);
    }
}
