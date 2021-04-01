package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.UsuarioRequest;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UsuarioValidator implements Validator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> aClass) {

        return UsuarioRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        UsuarioRequest usuarioRequest = (UsuarioRequest) o;

        Optional<Usuario> temLogin = usuarioRepository.findByLogin(usuarioRequest.getLogin());

        if(temLogin.isPresent()){
            errors.rejectValue("login", null, "E-mail já cadastrado"
                    + usuarioRequest.getLogin());
        }

    }
}
