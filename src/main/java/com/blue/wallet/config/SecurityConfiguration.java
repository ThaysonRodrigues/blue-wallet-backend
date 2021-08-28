package com.blue.wallet.config;

import com.blue.wallet.controller.uri.CadastroURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
     * Aqui serao colocadas as configuracoes de autorizacao
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, CadastroURI.PUBLIC_HTTP_POST_REQUESTS).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
               // .and()
               // .addFilterBefore(
              //          new AuthTokenFilter(this.tokenService, this.usuarioRepo),
               //         UsernamePasswordAuthenticationFilter.class
             //   );
    }

    /*
     * Aqui se o projeto fosse web estilo JSF onde o
     * backend controla o envio do html, seria colocado
     * as configuracoes estaticas do projeto (images, css...)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
                "/swagger-resources/**");
    }
}
