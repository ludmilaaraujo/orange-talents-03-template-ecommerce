package br.com.treino.mercadoLivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutencitacaoService autenticacaoService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/usuario").permitAll()
                .antMatchers( "/produto").permitAll()
                .antMatchers( "/subCategoria").permitAll()
                .antMatchers( "/categoria").permitAll()
                .antMatchers( "/produtos/{id}/imagens").permitAll()
                .antMatchers( "/opiniao").permitAll()
                .antMatchers( "/pergunta").permitAll()
                .antMatchers( "/produtos/{id}").permitAll()
                .antMatchers( "/compras").permitAll()

                .anyRequest().authenticated()
                .and().csrf().disable().formLogin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/h2-console/**");
    }

}
