package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.ExisteEntidade;
import br.com.treino.mercadoLivre.anotacoes.UniqueValue;
import br.com.treino.mercadoLivre.entidades.Opiniao;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoRequest {
    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;
    @NotBlank
    @UniqueValue(domainClass = Opiniao.class, fieldName = "titulo")
    private String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;
    @NotNull
    @ExisteEntidade(domainClass = Usuario.class, fieldName = "id")
    private Long idUsuario;
    @NotNull
    @ExisteEntidade(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    public OpiniaoRequest(@Min(1) @Max(5) @NotNull Integer nota,
                          @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
                          @NotNull Long idUsuario,
                          @NotNull Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
    }

    public Opiniao convetToEntity(ProdutoRepository produtoRepository,
                                  UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.getOne(idUsuario);
        Produto produto = produtoRepository.getOne(idProduto);
        return new Opiniao(nota, titulo, descricao, usuario, produto);
    }
}
