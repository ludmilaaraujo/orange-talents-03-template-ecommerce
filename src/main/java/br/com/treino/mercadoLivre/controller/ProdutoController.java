package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.request.ProdutoRequest;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import br.com.treino.mercadoLivre.validator.ProibeCaracteristicaRepetidaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicaRepetidaValidator());
    }

    @PostMapping(value = "/produto")
    @Transactional
    public String criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
        Produto produto = produtoRequest.converteToEntity(subCategoriaRepository,
                usuarioRepository);
        produtoRepository.save(produto);
        return produto.toString();


    }

}
