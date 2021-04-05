package br.com.treino.mercadoLivre.entidades;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instante = LocalDateTime.now();
    private @Email @NotBlank String login;
    private @NotBlank @Length(min = 6) String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();


    public Usuario(@Email @NotBlank String login,
            @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(login), "email não pode ser em branco");
        Assert.notNull(senhaLimpa, "a senha apresentou erro de validação");

        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

        @Override
        public String toString () {

        return "Usuario [login=" + login + ", senha=" + senha + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}