package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.anotacoes.ExisteEntidade;
import br.com.treino.mercadoLivre.anotacoes.UniqueValue;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequest {
    @NotNull
    @ExisteEntidade(domainClass = SubCategoria.class, fieldName = "id")
    private Long idSubCategoria;
    @UniqueValue(domainClass = Produto.class, fieldName = "nomeProduto")
    private String nomeProduto;
    @Positive
    private int quantidade;
    @NotBlank @Length(max = 1000)
    private String descricao;
    @Positive @NotNull
    private BigDecimal valor;
    @NotNull
    @ExisteEntidade(domainClass = Usuario.class, fieldName = "id")
    private Long idUsuario;
    @Size(min=3)
    @Valid
    private Set<CaracteristicaRequest> caracteristicas;

    public ProdutoRequest() {

    }
    public ProdutoRequest(@NotNull Long idSubCategoria, String nomeProduto, @Positive int quantidade,
                          @NotBlank @Length(max = 1000) String descricao,
                          @Positive @NotNull BigDecimal valor, @NotNull Long idUsuario,
                          @Size(min=3) @Valid Set<CaracteristicaRequest> caracteristicas) {
        this.idSubCategoria = idSubCategoria;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.caracteristicas= caracteristicas;
    }

    @Override
    public String toString(){
        return "ProdutoRequest [idSubCategoria" + idSubCategoria + " , nomeProduto " +
                nomeProduto + "quantidade" + quantidade + " ,descricao" + descricao +
                " ,valor" + valor + " ,dono" + idUsuario;
    }
    public Produto converteToEntity(SubCategoriaRepository subCategoriaRepository,
                                    UsuarioRepository usuarioRepository) {
        SubCategoria subCategoria
                = subCategoriaRepository.getOne(idSubCategoria);
        Usuario usuario = usuarioRepository.getOne(idUsuario);

        return new Produto(subCategoria,nomeProduto, quantidade, descricao,
                valor,usuario, caracteristicas);
    }


    public Set<String> buscaCaracteristicasRepetidas() {
        HashSet<String> nomesRepetidos = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (CaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomesRepetidos.add(nome)){
                resultados.add(nome);
            }
        }
        return resultados;
    }


    public boolean verificaSeNumeroEhMaiorQueCem(Integer num) {
        return num > 100;
    }
}
