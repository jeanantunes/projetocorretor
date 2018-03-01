package br.com.odontoprev.portalcorretor.config;

import br.com.odontoprev.portalcorretor.Service.LoginService;
import br.com.odontoprev.portalcorretor.controller.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Collections.singletonList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginservice;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getPrincipal().toString().replaceAll("^[0-9]", "");
        String password = authentication.getCredentials().toString();

        UsuarioSession usuario = autenticarServico(name, password);
        if (usuario != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, password, singletonList(new SimpleGrantedAuthority(usuario.getPerfil())));
            usernamePasswordAuthenticationToken.setDetails(usuario);
            return usernamePasswordAuthenticationToken;
        } else
            return null;
    }

    private UsuarioSession autenticarServico(String name, String password) {
        return loginservice.Autenticar("38330982874", "odonto2018");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
