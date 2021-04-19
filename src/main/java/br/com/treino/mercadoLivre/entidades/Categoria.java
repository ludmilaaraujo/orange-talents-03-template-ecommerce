package br.com.treino.mercadoLivre.entidades;

import br.com.treino.mercadoLivre.response.CategoriaResponse;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy="categoria")
    private List<SubCategoria> subCategorias;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
    }

    @Override
    public  String toString(){
        return "Categoria [nome=" + nome + "]";
    }

    public CategoriaResponse categoriaResponse(){

        return new CategoriaResponse(this.nome);
    }

}
