package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.response.CaracteristicasResponse;

import javax.persistence.*;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    public Caracteristica() {
    }

    public Caracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "[nome : " + nome + " descricao : " + descricao + "]";
    }

    public CaracteristicasResponse caracteristicasResponse(){
        return new CaracteristicasResponse(this.nome, this.descricao);
    }
}