package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Categoria;
import br.com.treino.mercadoLivre.request.CategoriaRequest;
import br.com.treino.mercadoLivre.response.CategoriaResponse;
import br.com.treino.mercadoLivre.resporitory.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(value = "/categoria")
    public CategoriaResponse criar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.converteToEntity();
        categoriaRepository.save(categoria);
        return categoria.categoriaResponse();
    }
}
