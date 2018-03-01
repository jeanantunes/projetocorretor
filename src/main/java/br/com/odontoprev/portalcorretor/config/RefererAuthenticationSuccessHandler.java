package br.com.odontoprev.portalcorretor.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.odontoprev.portalcorretor.controller.UsuarioSession;

@Component
public class RefererAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
            throws IOException, ServletException {    	
    	UsuarioSession usuario = (UsuarioSession) auth.getDetails();
    	req.getSession().setAttribute("usuario", usuario);
        if (auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(aut -> aut.equals("Corretora"))) {
            resp.sendRedirect("/corretor/homeCorretora");
        } else {
            resp.sendRedirect("/corretor/homeCorretor");
        }
    }
}
