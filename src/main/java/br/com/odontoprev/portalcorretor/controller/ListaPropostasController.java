package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.Service.dto.dashboardPropostasPF;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ListaPropostasController {

    @RequestMapping(value = "/others/listaPropostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        ListaPropostas listaPropostas = new ListaPropostas();

        List<dashboardPropostasPF> propostasPF = new ArrayList<>();
//        propostasPF.add(new dashboardPropostasPF(1,"Fulado de PF  1", "xuxu" , new Date(),"123123123123"));
//        propostasPF.add(new dashboardPropostasPF(1,"Fulado de PF 2", "Aprovado" , new Date(),"123123123123"));
//        propostasPF.add(new dashboardPropostasPF(1,"Fulado de PF 1", "ajsaj" , new Date(),"123123123123"));


        List<dashboardPropostasPF> propostasPME = new ArrayList<>();
//        propostasPME.add(new dashboardPropostasPF(1,"Fulado de PME 1", "Aprovado" , new Date(),"123123123123"));
//        propostasPME.add(new dashboardPropostasPF(1,"Fulado de PME 2", "Aprovado" , new Date(),"123123123123"));
//        propostasPME.add(new dashboardPropostasPF(1,"Fulado de PME 3", "Aprovado" , new Date(),"123123123123"));
//        propostasPME.add(new dashboardPropostasPF(1,"Fulado de PME 1", "Aprovado" , new Date(),"123123123123"));
//        propostasPME.add(new dashboardPropostasPF(1,"Fulado de PME 3", "Aprovado" , new Date(),"123123123123"));

        listaPropostas.setPropostaPF(propostasPF);
        listaPropostas.setPropostaPME(propostasPME);
        listaPropostas.setTotalPF(propostasPF.size() );
        listaPropostas.setTotalPME(propostasPME.size() );
        listaPropostas.setTotalVidas(propostasPF.size() + propostasPME.size() );


        return new ModelAndView("/corretor/others/listaPropostas", "listaPropostas", listaPropostas);
    }

}
