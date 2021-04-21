package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.ExisteEntidade;
import br.com.treino.mercadoLivre.entidades.Pergunta;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PerguntaRequest {

    private LocalDateTime instante = LocalDateTime.now();
    @NotBlank
    private String pergunta;
    @NotNull
    @ExisteEntidade(domainClass = Usuario.class, fieldName = "id")
    private Long idUsuario;
    @NotNull
    @ExisteEntidade(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    public PerguntaRequest(String pergunta, @NotNull Long idUsuario, @NotNull Long idProduto) {
        this.pergunta = pergunta;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
    }

    public Pergunta convertToEntity(ProdutoRepository produtoRepository,
                                    UsuarioRepository usuarioRepository) {
            Produto produto = produtoRepository.getOne(idProduto);
            Usuario usuario = usuarioRepository.getOne(idUsuario);
            return new Pergunta(pergunta, usuario, produto);
    }


}
