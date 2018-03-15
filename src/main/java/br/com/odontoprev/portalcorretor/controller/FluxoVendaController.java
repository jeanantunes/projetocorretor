package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.model.VendaPf;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@SessionScope
public class FluxoVendaController {

    VendaPme vendaPme = new VendaPme();
    VendaPf vendaPf = new VendaPf();
    UsuarioSession usuario = new UsuarioSession();
    HttpSession session;

    public void inicioFluxoVenda(HttpSession session) throws IOException {
        this.session = session;
    }

    public void add(String nomeObjeto, Object objeto){
        session.setAttribute(nomeObjeto,objeto);
    }

    public void finalizaSessao(){
        session.invalidate();
    }


}
