package br.com.odontoprev.portalcorretor.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getPrincipal().toString().replaceAll("^[0-9]", "");
        String password = authentication.getCredentials().toString();

        if (autenticarServico(name, password)) {
            return new UsernamePasswordAuthenticationToken(name, password, Collections.singletonList(new SimpleGrantedAuthority(name.length() == 11? "CORRETOR": "CORRETORA")));
        } else
            return null;
    }

    private Boolean autenticarServico(String name, String password) {
        return true;
        //TODO: Implementar login de autenticação
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
