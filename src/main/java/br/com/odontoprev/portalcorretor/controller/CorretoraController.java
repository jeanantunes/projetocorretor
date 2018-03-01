package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.DashboardCorretora;
import br.com.odontoprev.portalcorretor.model.Proposta;
import br.com.odontoprev.portalcorretor.model.PropostaStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

import static br.com.odontoprev.portalcorretor.model.PropostaStatus.APROVACAO;
import static br.com.odontoprev.portalcorretor.model.PropostaStatus.CRITICADAS;
import static br.com.odontoprev.portalcorretor.model.PropostaStatus.SUCESSO;

@Controller
public class CorretoraController {

    @RequestMapping(value = "/corretor/homeCorretora", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        DashboardCorretora corretora = new DashboardCorretora();

        List<Proposta> propostas = new ArrayList<>();
        propostas.add(new Proposta("Vendedor 1", "PF", new Date(), 120d, 1, APROVACAO));
        propostas.add(new Proposta("Vendedor 2", "PME", new Date(), 140d, 2, SUCESSO));
        propostas.add(new Proposta("Vendedor 3", "PME", new Date(), 440d, 3,SUCESSO));
        propostas.add(new Proposta("Vendedor 1", "PF", new Date(), 120d, 1, CRITICADAS));
        propostas.add(new Proposta("Vendedor 3", "PME", new Date(), 440d, 3,SUCESSO));


        corretora.setCountCorretoresAprovacao(propostas.stream().filter(a -> a.getStatus() == APROVACAO ).count());
        corretora.setCountCorretoresCriticadas(propostas.stream().filter(a -> a.getStatus() == CRITICADAS ).count());
        corretora.setCountCorretoresSucesso(propostas.stream().filter(a -> a.getStatus() == SUCESSO ).count());
        corretora.setPropostas(propostas);
        corretora.setTipoPlano(propostas.stream().map(Proposta::getTipoPlano).distinct().collect(Collectors.toList()));
        corretora.setVendedores(propostas.stream().map(Proposta::getVendedor).distinct().collect(Collectors.toList()));
        corretora.setValorPessoaFisica(propostas.stream().filter(a -> a.getTipoPlano().equals("PF")).mapToDouble(Proposta::getValor).sum());
        corretora.setValorPME(propostas.stream().filter(a -> a.getTipoPlano().equals("PME")).mapToDouble(Proposta::getValor).sum());

//        Map<String, Object> models = new HashMap<>();
//        models.put("corretora", corretora);
        // models.put("usuario", usuario);
        return new ModelAndView("/corretor/homeCorretora", "corretora", corretora);
    }

}
