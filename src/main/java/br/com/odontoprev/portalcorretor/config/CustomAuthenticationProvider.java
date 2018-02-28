package br.com.odontoprev.portalcorretor.config;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import br.com.odontoprev.portalcorretor.controller.Usuario;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {



 /*   public CustomAuthenticationProvider(HttpSession session)
    {
        this.session = session;
    }*/


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getPrincipal().toString().replaceAll("^[0-9]", "");
        String password = authentication.getCredentials().toString();
        
        Usuario usuario = autenticarServico(name, password);
		if (usuario!=null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, password, Collections.singletonList(new SimpleGrantedAuthority(name.length() == 11? "CORRETOR": "CORRETORA")));
            usernamePasswordAuthenticationToken.setDetails(usuario);
			return usernamePasswordAuthenticationToken;
        } else
            return null;
    }

    private Usuario autenticarServico(String name, String password) {


        LoginResponse response = new LoginResponse();
        response.setCodigo(123);
        response.setNome("Rafael Berlezi");
        response.setCodigoCorretora(567);
        response.setNomeCorretora("Odontoprev");
        response.setPerfil("Corretor");
        response.setDocumento("30555386848");
         

        return new Usuario().setDados(response);        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
