package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.component.UploaderImagem;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.request.ImagensRequest;
import br.com.treino.mercadoLivre.request.ProdutoRequest;
import br.com.treino.mercadoLivre.response.ProdutoResponse;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.SubCategoriaRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import br.com.treino.mercadoLivre.validator.ProibeCaracteristicaRepetidaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UploaderImagem uploaderImagem;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicaRepetidaValidator());
    }

    @PostMapping(value = "/produto")
    @Transactional
    public ProdutoResponse criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
        Produto produto = produtoRequest.converteToEntity(subCategoriaRepository,
                usuarioRepository);
        produtoRepository.save(produto);
        return produto.produtoResponse();
    }
    @PostMapping(value = "/produtos/{id}/imagens")
    @Transactional
    public ProdutoResponse adicionaImagem(@RequestBody @Valid @PathVariable ("id")Long id,
                                                ImagensRequest imagensRequest){
        Produto produto = produtoRepository.getOne(id);
        List<String> links = uploaderImagem.envia(imagensRequest.getImagens());
        produto.associarImagens(links);
        produtoRepository.save(produto);
        return produto.produtoResponse();
    }


}
