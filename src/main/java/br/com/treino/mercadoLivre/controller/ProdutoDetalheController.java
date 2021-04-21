package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.response.ProdutoResponse;
import br.com.treino.mercadoLivre.resporitory.OpiniaoRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @GetMapping(value = "/produtos/{id}")
    public ProdutoResponse produtoVisualizado(@PathVariable("id") Long id){
        Produto produto = produtoRepository.getOne(id);
        return produto.produtoResponse(opiniaoRepository);
    }

}
