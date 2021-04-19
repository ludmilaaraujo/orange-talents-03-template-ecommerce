package br.com.treino.mercadoLivre.response;

import br.com.treino.mercadoLivre.entidades.Caracteristica;

import java.util.HashSet;
import java.util.Set;

public class CaracteristicasResponse {
    private String nome;
    private String descricao;

    public CaracteristicasResponse() {
    }

    public CaracteristicasResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicasResponse> caracteristicasResponseSet(Set<Caracteristica> caracteristicasEntitys) {
        Set<CaracteristicasResponse> setDevolvido = new HashSet<CaracteristicasResponse>();
        for (Caracteristica caracteristica : caracteristicasEntitys) {
            setDevolvido.add(caracteristica.caracteristicasResponse());
        }
        return setDevolvido;
    }
}
