package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.Proposta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.odontoprev.portalcorretor.model.PropostaStatus.*;

@Controller
public class ListaPropostasController {

    @RequestMapping(value = "/others/listaPropostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        ListaPropostas listaPropostas = new ListaPropostas();

        List<Proposta> propostas = new ArrayList<>();
        propostas.add(new Proposta("Vendedor 1", "PF", new Date(), 120d, 1, APROVACAO));
        propostas.add(new Proposta("Vendedor 2", "PME", new Date(), 140d, 2, SUCESSO));
        propostas.add(new Proposta("Vendedor 3", "PME", new Date(), 440d, 3, SUCESSO));
        propostas.add(new Proposta("Vendedor 1", "PF", new Date(), 120d, 1, CRITICADAS));
        propostas.add(new Proposta("Vendedor 3", "PME", new Date(), 440d, 3, SUCESSO));


        listaPropostas.setPropostaPF(propostas.stream().filter(a -> a.getTipoPlano().equals("PF")).collect(Collectors.toList()));
        listaPropostas.setPropostaPME(propostas.stream().filter(a -> a.getTipoPlano().equals("PME")).collect(Collectors.toList()));
        listaPropostas.setTotalPF(propostas.stream().filter(a -> a.getTipoPlano().equals("PF")).mapToLong(Proposta::getVidas).sum());
        listaPropostas.setTotalPME(propostas.stream().filter(a -> a.getTipoPlano().equals("PME")).mapToLong(Proposta::getVidas).sum());
        listaPropostas.setTotalVidas(propostas.stream().mapToLong(Proposta::getVidas).sum());


        return new ModelAndView("/corretor/others/listaPropostas", "listaPropostas", listaPropostas);
    }

}
