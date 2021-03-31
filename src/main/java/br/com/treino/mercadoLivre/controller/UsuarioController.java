package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.UsuarioRequest;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping (value = "/usuario")
    @Transactional
    public String criar(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.coverteToEntity();
        usuarioRepository.save(usuario);
        return usuario.toString();
    }



}
