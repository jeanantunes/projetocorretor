package br.com.odontoprev.portalcorretor.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.DashService;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import br.com.odontoprev.portalcorretor.service.dto.Proposta;
import br.com.odontoprev.portalcorretor.service.entity.FiltroStatusProposta;

@Controller
public class ForcaVendaController {

    @Autowired
    private DashService dashService;
    
    @Autowired
    private ForcaVendaService forcaVendaService;

    @RequestMapping(value = "forcavenda/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());

        ListaPropostas corretora = new ListaPropostas();
        List<Proposta> propostasPME = propostaPME.getDashboardPropostasPME();
        List<Proposta> propostasPF = propostaPF.getDashboardPropostasPF();

        Stream<Proposta> concat = Stream.concat(propostasPF.stream(), propostasPME.stream());

        Long aprovada = concat.filter(p -> p.getStatusVenda().equals("Aprovado")).count();
        Long criticadas = propostasPF.size() + propostasPME.size() - aprovada;

        corretora.setPropostaPF(propostasPME);
        corretora.setPropostaPME(propostasPF);
        corretora.setTotalSucesso(aprovada.intValue() );
        corretora.setTotalCriticadas(criticadas.intValue());

        double totalValorPF = propostaPF.getDashboardPropostasPF().stream().mapToDouble(Proposta::getValor).sum();
        double totalValorPME = propostaPME.getDashboardPropostasPME().stream().mapToDouble(Proposta::getValor).sum();

        corretora.setTotalValorPF(totalValorPF);
        corretora.setPercenteValorPF( totalValorPF> totalValorPME ? 100:  totalValorPF == 0 ? 0 : 50 );
        corretora.setTotalValorPME(totalValorPME);
        corretora.setPercenteValorPME(  totalValorPME > totalValorPF ? 100:  totalValorPME  == 0 ? 0 : 50 );

        return new ModelAndView("forcavenda/home", "corretor", corretora);
    }

    @RequestMapping("forcavenda/cadastro/editar")
    public ModelAndView index(HttpSession session) {

    	UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());

        return new ModelAndView("forcavenda/cadastro/editar", "forcaVenda", forcaVenda);
    }

    @RequestMapping(value = "forcavenda/cadastro/salvar", method = RequestMethod.POST)
    public ModelAndView salvar(HttpSession session, @Valid @ModelAttribute("forcaVenda") ForcaVenda forcaVendaParam, BindingResult result) {

    	if (result.hasErrors()) {
    		return new ModelAndView("forcavenda/cadastro/editar", "forcaVenda", forcaVendaParam);

    	} else {
    		
    		if (!forcaVendaParam.getSenha().equals(forcaVendaParam.getConfirmaSenha())) {
    			return new ModelAndView("forcavenda/cadastro/editar", "forcaVenda", forcaVendaParam);
    		}

        	UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

            ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());
            forcaVenda.setCelular(forcaVendaParam.getCelular());
            forcaVenda.setEmail(forcaVendaParam.getEmail());
            forcaVenda.setSenha(forcaVendaParam.getSenha());

            forcaVendaService.Alterar(forcaVenda);
            
            return this.home(session);
    	}
    }
    
    @RequestMapping(value = "alertas/{statusProposta}", method = RequestMethod.GET)
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