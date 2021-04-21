package br.com.treino.mercadoLivre.component;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void enviar(String login){
        System.out.println("Enviando e-mail fake: " + login);
    }
}
