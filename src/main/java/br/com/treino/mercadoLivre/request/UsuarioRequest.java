package br.com.treino.mercadoLivre.request;

import br.com.treino.mercadoLivre.entidades.Usuario;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UsuarioRequest {
    @NotNull @NotBlank
    @Email
    private String login;
    @Length(min = 6)
    private String senha;

    private LocalDateTime instante = LocalDateTime.now();

    public UsuarioRequest(@NotBlank @Email String login, @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario coverteToEntity() {
        return new Usuario(this.login, this.senha);
    }
}
