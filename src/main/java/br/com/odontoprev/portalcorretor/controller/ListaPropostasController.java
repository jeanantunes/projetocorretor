package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.service.DashService;
import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.service.dto.Proposta;
import br.com.odontoprev.portalcorretor.service.entity.FiltroStatusProposta;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ListaPropostasController {

    @Autowired
    DashService dashService;

    @RequestMapping(value = "lista-propostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaPropostas listaPropostas = new ListaPropostas();

        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        List<Proposta> dashboardPropostasPME = propostaPME.getDashboardPropostasPME();
        listaPropostas.setPropostaPME(dashboardPropostasPME);
        listaPropostas.setTotalPME(dashboardPropostasPME.size());


        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());
        List<Proposta> dashboardPropostasPF = propostaPF.getDashboardPropostasPF();
        listaPropostas.setPropostaPF(dashboardPropostasPF);
        listaPropostas.setTotalPF(dashboardPropostasPF.size());

        listaPropostas.setTotal(dashboardPropostasPF.size() + dashboardPropostasPME.size());


        return new ModelAndView("lista-propostas", "listaPropostas", listaPropostas);
    }

}
