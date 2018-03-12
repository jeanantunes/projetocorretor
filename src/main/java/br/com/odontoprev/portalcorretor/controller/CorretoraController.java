package br.com.odontoprev.portalcorretor.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.CorretoraService;
import br.com.odontoprev.portalcorretor.service.DashService;
import br.com.odontoprev.portalcorretor.service.EnderecoService;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.service.dto.EnderecoResponse;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import br.com.odontoprev.portalcorretor.service.dto.Proposta;
import br.com.odontoprev.portalcorretor.service.entity.FiltroStatusProposta;

@Controller
public class CorretoraController {

    @Autowired
    DashService dashService;

    @Autowired
    ForcaVendaService forcaVendaService;
    
    @Autowired
    CorretoraService corretoraService;
    
    @Autowired
    EnderecoService enderecoService;


    @RequestMapping(value = "corretora/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {    	
    	
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        //TODO retornar valores nas propostas
        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());
        DashResponse[] dashs = dashService.obterPorDocumento(null,null,usuario.getDocumento());        

        ListaPropostas corretora = new ListaPropostas();
        List<Proposta> propostasPME = propostaPME.getDashboardPropostasPME();
        List<Proposta> propostasPF = propostaPF.getDashboardPropostasPF();


        List<ForcaVenda> forcaVendas = forcaVendaService.ObterListaPorCorretora(usuario.getCodigoCorretora());

        Long aguardando = forcaVendas
                .stream()
                .filter(i -> i.getStatusForcaVenda().equals("Aguardando Aprovação"))
                .count();
        corretora.setCountCorretoresAprovacao(aguardando.intValue());

        List<Proposta> dashsConverter = Arrays.asList(dashs).stream()
        		.map(
        		dash -> new Proposta(
	        				dash.getNomeForca(),
	        				dash.getTipoVenda(),
	        				new Date(),
	        				dash.getValorTotal()
	        				)
        		)
        		.collect(Collectors.toList());
        
        Stream<Proposta> propostas = Stream.concat(propostasPF.stream().peek(i -> i.setTipoPlano("PF")), propostasPME.stream().peek(i -> i.setTipoPlano("PME")));	                
        	
        
        corretora.setPropostas(dashsConverter);
        Long aprovada = propostas.filter(p -> p.getStatusVenda().equals("Aprovado")).count();
        Long criticadas = propostasPF.size() + propostasPME.size() - aprovada;

        corretora.setPropostaPF(propostasPME);
        corretora.setPropostaPME(propostasPF);

        corretora.setTotalSucesso(aprovada.intValue());
        corretora.setTotalCriticadas(criticadas.intValue());
        
      //  double totalValorPF = propostaPF.getDashboardPropostasPF().stream().mapToDouble(Proposta::getValor).sum();
      //  double totalValorPME = propostaPME.getDashboardPropostasPME().stream().mapToDouble(Proposta::getValor).sum();
        double totalValorPF = dashsConverter.stream().filter(p -> p.getTipoPlano().equals("PF")).mapToDouble(Proposta::getValor).sum();
        double totalValorPME = dashsConverter.stream().filter(p -> p.getTipoPlano().equals("PME")).mapToDouble(Proposta::getValor).sum();

        corretora.setTotalValorPF(new BigDecimal(totalValorPF).setScale(2, BigDecimal.ROUND_UP).doubleValue());
        corretora.setPercenteValorPF(totalValorPF > totalValorPME ? 100 : totalValorPF == 0 ? 0 : 50);

        corretora.setTotalValorPME(new BigDecimal(totalValorPME).setScale(2, BigDecimal.ROUND_UP).doubleValue());
        corretora.setPercenteValorPME(totalValorPME > totalValorPF ? 100 : totalValorPME == 0 ? 0 : 50);


        return new ModelAndView("corretora/home", "corretora", corretora);
    }
   
	@RequestMapping(value = "corretora/cadastro/editar", method = RequestMethod.GET)
    public ModelAndView Editar(HttpSession session) {

    	UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

    	Corretora corretora = corretoraService.ObterDadosCorretora(usuario.getDocumento());
    	
    	//EnderecoResponse enderecoResponse = enderecoService.ObterEnderecoCorretora("03803030");

    	return new ModelAndView("corretora/cadastro/editar", "corretora", corretora);
    }
  
    @RequestMapping(value = "lista-propostas/{statusProposta}", method = RequestMethod.GET)
    public ModelAndView Proposta(@PathVariable String statusProposta, HttpSession session) {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaPropostas listaPropostas = new ListaPropostas();

        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(statusProposta.toUpperCase().equals("APROVADO") ? FiltroStatusProposta.APROVADO : FiltroStatusProposta.CRITICADO, usuario.getDocumento());

        List<Proposta> dashboardPropostasPME = propostaPME.getDashboardPropostasPME();
        listaPropostas.setPropostaPME(dashboardPropostasPME);
        listaPropostas.setTotalPME(dashboardPropostasPME.size());

        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(statusProposta.toUpperCase().equals("APROVADO") ? FiltroStatusProposta.APROVADO : FiltroStatusProposta.CRITICADO, usuario.getDocumento());
        List<Proposta> dashboardPropostasPF = propostaPF.getDashboardPropostasPF();
        listaPropostas.setPropostaPF(dashboardPropostasPF);
        listaPropostas.setTotalPF(dashboardPropostasPF.size());

        listaPropostas.setTotal(dashboardPropostasPF.size() + dashboardPropostasPME.size());

        return new ModelAndView("lista-propostas", "listaPropostas", listaPropostas);

    }


}
