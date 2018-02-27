package br.com.odontoprev.portalcorretor.config;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getPrincipal().toString().replaceAll("^[0-9]","");
		String password = authentication.getCredentials().toString();

		if (autenticarServico(name, password)) {
            return new UsernamePasswordAuthenticationToken(name, password);
        }
		 else
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
