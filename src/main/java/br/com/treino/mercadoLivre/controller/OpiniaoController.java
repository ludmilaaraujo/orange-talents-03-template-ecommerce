package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Opiniao;
import br.com.treino.mercadoLivre.request.OpiniaoRequest;
import br.com.treino.mercadoLivre.response.OpiniaoResponse;
import br.com.treino.mercadoLivre.resporitory.OpiniaoRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpiniaoController {

    @Autowired
    private OpiniaoRepository opiniaoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/imagens")
    @Transactional
    public OpiniaoResponse adicionaOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest) {
        Opiniao opiniao = opiniaoRequest.convetToEntity(produtoRepository, usuarioRepository);
        opiniaoRepository.save(opiniao);
        return opiniao.opiniaoResponse();
    }
}
