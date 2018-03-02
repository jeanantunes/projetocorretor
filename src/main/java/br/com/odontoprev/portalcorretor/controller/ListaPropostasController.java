package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.Service.DashService;
import br.com.odontoprev.portalcorretor.Service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ListaPropostasController {

    @Autowired
    DashService dashService;

    @RequestMapping(value = "/lista-propostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaPropostas listaPropostas = new ListaPropostas();

        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        listaPropostas.setPropostaPME(propostaPME.getDashboardPropostasPME());
        listaPropostas.setTotalPME(propostaPME.getDashboardPropostasPME().size());


        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());
        listaPropostas.setPropostaPF(propostaPF.getDashboardPropostasPF());
        listaPropostas.setTotalPF(propostaPF.getDashboardPropostasPF().size());

        listaPropostas.setTotal(propostaPME.getDashboardPropostasPF().size() + propostaPF.getDashboardPropostasPME().size());


        return new ModelAndView("/lista-propostas", "listaPropostas", listaPropostas);
    }

}
