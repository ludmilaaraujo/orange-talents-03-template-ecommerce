package br.com.treino.mercadoLivre.response;

import java.time.LocalDateTime;

public class UsuarioResponse {

    private String login;
    private LocalDateTime instante = LocalDateTime.now();

    public UsuarioResponse(String login, LocalDateTime instante) {
        this.login = login;
        this.instante = instante;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getInstante() {
        return instante;
    }
}
