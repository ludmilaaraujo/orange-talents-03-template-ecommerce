package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.UsuarioRequest;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsuarioController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioValidator impedeEmailDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(impedeEmailDuplicadoValidator);
    }

    @PostMapping (value = "/usuario")
    @Transactional
    public String criar(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.toUsuario();
        manager.persist(usuario);
        return usuario.toString();
    }



}
