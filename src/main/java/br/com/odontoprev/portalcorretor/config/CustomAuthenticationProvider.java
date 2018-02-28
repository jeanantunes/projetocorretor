package br.com.odontoprev.portalcorretor.config;
import br.com.odontoprev.portalcorretor.Service.LoginService;
import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

private HttpSession session;

    public CustomAuthenticationProvider(HttpSession session)
    {
        this.session = session;
    }


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

//        LoginService loginService = new LoginService();
//        LoginResponse response =  loginService.Autenticar(name, password);
//
//        if(response == null)
//        {
//            return false;
//        }

        LoginResponse response = new LoginResponse();
        response.setCodigo(123);
        response.setNome("Rafael Berlezi");
        response.setCodigoCorretora(567);
        response.setNomeCorretora("Odontoprev");
        response.setPerfil("Corretor");
        response.setDocumento("30555386848");
        session.setAttribute("Usuario", response);

        return true;
        //TODO: Implementar login de autenticação
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
