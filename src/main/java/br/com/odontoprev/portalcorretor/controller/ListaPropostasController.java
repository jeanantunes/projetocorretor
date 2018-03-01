package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.Service.DashService;
import br.com.odontoprev.portalcorretor.Service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
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

    @RequestMapping(value = "/others/listaPropostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());

        ListaPropostas listaPropostas = new ListaPropostas();
        listaPropostas.setPropostaPF(propostaPF.getDashboardPropostasPME());
        listaPropostas.setPropostaPME(propostaPME.getDashboardPropostasPME());

        listaPropostas.setTotalPF(propostaPF.getDashboardPropostasPME().size());
        listaPropostas.setTotalPME(propostaPME.getDashboardPropostasPME().size());
        listaPropostas.setTotalVidas(propostaPME.getDashboardPropostasPME().size() + propostaPF.getDashboardPropostasPME().size());


        return new ModelAndView("/corretor/others/listaPropostas", "listaPropostas", listaPropostas);
    }

}
