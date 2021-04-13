package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.entidades.Caracteristica;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class CaracteristicaRequest {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String descricao;

    @Deprecated
    public CaracteristicaRequest() {

    }

    public CaracteristicaRequest(@NotEmpty String nome, @NotEmpty String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<Caracteristica> converteToCaracttEntity(Set<CaracteristicaRequest> lista){
        Set<Caracteristica> caracteristicas =  new HashSet<Caracteristica>();
        for (CaracteristicaRequest item: lista) {
            caracteristicas.add(new Caracteristica(item.getNome(), item.getDescricao()));
        }
        return caracteristicas;
    }
}
