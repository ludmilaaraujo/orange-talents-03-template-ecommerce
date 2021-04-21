package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.component.EnviaEmail;
import br.com.treino.mercadoLivre.entidades.Pergunta;
import br.com.treino.mercadoLivre.request.PerguntaRequest;
import br.com.treino.mercadoLivre.response.PerguntaResponse;
import br.com.treino.mercadoLivre.resporitory.PerguntaRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaController {
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnviaEmail enviaEmail;


    @PostMapping("/pergunta")
    @Transactional
    public PerguntaResponse criaPergunta(@RequestBody @Valid
                                           PerguntaRequest perguntaRequest){
        Pergunta pergunta = perguntaRequest.convertToEntity(produtoRepository,
                usuarioRepository);
        perguntaRepository.save(pergunta);
        enviaEmail.enviar(pergunta.getEmailVendedor());
        return pergunta.perguntaResponse();
    }

}


