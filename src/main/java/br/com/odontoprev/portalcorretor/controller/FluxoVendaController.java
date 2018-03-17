package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.model.VendaPf;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.Plano;
import br.com.odontoprev.portalcorretor.service.dto.Titulare;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@SessionScope
public class FluxoVendaController {

private Empresa empresa = new Empresa();
private Titulare titular = new Titulare();

    UsuarioSession usuario = new UsuarioSession();
    HttpSession sessionfluxoVenda;

    public void inicioFluxoVenda(HttpSession session) throws IOException {
        sessionfluxoVenda = session;
    }

    public void add(String nomeObjeto, Object objeto){
        sessionfluxoVenda.setAttribute(nomeObjeto,objeto);
    }

    public void finalizaSessaoFluxoVenda(){
        //sessionfluxoVenda.setAttribute();
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Titulare getTitular() {
        return titular;
    }

    public void setTitular(Titulare titular) {
        this.titular = titular;
    }
}
