package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.request.SubCategoriaRequest;
import br.com.treino.mercadoLivre.resporitory.CategoriaRepository;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class SubCategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @PostMapping (value = "/subCategoria")
    @Transactional
    public String criaSubCategoria(@RequestBody @Valid SubCategoriaRequest subCategoriaRequest){
        SubCategoria subCategoria = subCategoriaRequest.converteToEntity(categoriaRepository);
        subCategoriaRepository.save(subCategoria);
        return subCategoria.toString();
    }

}
