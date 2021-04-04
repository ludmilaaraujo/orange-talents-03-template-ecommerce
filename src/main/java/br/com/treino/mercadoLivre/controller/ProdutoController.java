package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Categoria;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.request.ProdutoRequest;
import br.com.treino.mercadoLivre.resporitory.CategoriaRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    @PostMapping(value = "/produto")
    @Transactional
    public String criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
        Produto produto = produtoRequest.converteToEntity(subCategoriaRepository);
        produtoRepository.save(produto);
        return produto.toString();
    }

}
