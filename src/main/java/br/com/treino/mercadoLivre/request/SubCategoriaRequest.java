package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.ExisteEntidade;
import br.com.treino.mercadoLivre.anotacoes.UniqueValue;
import br.com.treino.mercadoLivre.entidades.Categoria;
import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.resporitory.CategoriaRepository;

import javax.validation.constraints.NotNull;

public class SubCategoriaRequest {
    @NotNull
    @ExisteEntidade(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @UniqueValue(domainClass = SubCategoria.class, fieldName = "nomeSubCategoria")
    private String nomeSubCategoria;

    public SubCategoriaRequest(@NotNull Long idCategoria, String nomeSubCategoria) {
        this.idCategoria = idCategoria;
        this.nomeSubCategoria = nomeSubCategoria;
    }

    public SubCategoria converteToEntity(CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getOne(idCategoria);
        return new SubCategoria(categoria, nomeSubCategoria);
    }
}
