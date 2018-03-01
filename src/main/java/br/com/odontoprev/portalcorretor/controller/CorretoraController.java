package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.Service.DashService;
import br.com.odontoprev.portalcorretor.Service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.Service.dto.Proposta;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CorretoraController {


    @Autowired
    DashService dashService;

    @RequestMapping(value = "/corretor/homeCorretora", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        //TODO retornar valores nas propostas


        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());


        ListaPropostas corretora = new ListaPropostas();
        List<Proposta> propostasPME = propostaPF.getPropostasPME();
        List<Proposta> propostasPF = propostaPME.getPropostasPF();


        //TODO retornar numero de corretores para aprovação
        corretora.setCountCorretoresAprovacao(2);



        Stream<Proposta> concat = Stream.concat(propostasPF.stream().peek(i -> i.setTipoPlano("PF")) , propostasPME.stream().peek(i-> i.setTipoPlano("PME")));

        List<Proposta> propostas = concat.collect(Collectors.toList());
        corretora.setPropostas(propostas);
        Long aprovada = propostas.stream().filter(p -> p.getStatusVenda().equals("Aprovada")).count();
        Long criticadas = propostasPF.size() + propostasPME.size() - aprovada;

        corretora.setPropostaPF(propostasPME);
        corretora.setPropostaPME(propostasPF);


        corretora.setTotalSucesso(aprovada.intValue() );
        corretora.setTotalCriticadas(criticadas.intValue());



        double totalValorPF = propostaPF.getPropostasPF().stream().mapToDouble(Proposta::getValor).sum();
        double totalValorPME = propostaPF.getPropostasPME().stream().mapToDouble(Proposta::getValor).sum();

        corretora.setTotalValorPF(totalValorPF);
        corretora.setPercenteValorPF( totalValorPF> totalValorPME ? 100:  totalValorPF == 0 ? 0 : 50 );

        corretora.setTotalValorPME(totalValorPME);
        corretora.setPercenteValorPME(  totalValorPME > totalValorPF ? 100:  totalValorPME  == 0 ? 0 : 50 );


        return new ModelAndView("/corretor/homeCorretora", "corretora", corretora);
    }

}
