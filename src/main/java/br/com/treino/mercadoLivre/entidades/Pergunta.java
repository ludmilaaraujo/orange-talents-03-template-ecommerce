package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.response.PerguntaResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instante = LocalDateTime.now();
    private String pergunta;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Produto produto;

    public Pergunta(String pergunta, Usuario usuario, Produto produto) {
        this.pergunta = pergunta;
        this.usuario = usuario;
        this.produto = produto;
    }

    public PerguntaResponse perguntaResponse(){
        return new PerguntaResponse(this.pergunta, usuario.usuarioResponse(),
                produto.produtoResponse());
    }

    public String getEmailVendedor(){
        return usuario.getLogin();
    }
}
