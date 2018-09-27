package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.DashService;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import br.com.odontoprev.portalcorretor.service.dto.Login;
import br.com.odontoprev.portalcorretor.service.dto.Proposta;
import br.com.odontoprev.portalcorretor.service.entity.FiltroStatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

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

        Long aprovada = concat.filter(a -> a.getStatusVenda().equals("Proposta concluida com sucesso")).count();
        DashboardPropostas criticadasPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.CRITICADO, usuario.getDocumento());
        DashboardPropostas criticadasPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.CRITICADO, usuario.getDocumento());
        ListaPropostas listaCriticadas = new ListaPropostas();
        List<Proposta> criticadaPME = criticadasPME.getDashboardPropostasPME();
        List<Proposta> criticadaPF = criticadasPF.getDashboardPropostasPF();

        Long criticadas = Long.valueOf(criticadaPF.size() + criticadaPME.size());

        corretora.setPropostaPF(propostasPME);
        corretora.setPropostaPME(propostasPF);
        corretora.setTotalSucesso(aprovada.intValue());
        corretora.setTotalCriticadas(criticadas.intValue());

        double totalValorPF = propostaPF.getDashboardPropostasPF().stream().mapToDouble(Proposta::getValor).sum();
        double totalValorPME = propostaPME.getDashboardPropostasPME().stream().mapToDouble(Proposta::getValor).sum();

        corretora.setTotalValorPF(totalValorPF);
        corretora.setPercenteValorPF(totalValorPF > totalValorPME ? 100 : totalValorPF == 0 ? 0 : 50);
        corretora.setTotalValorPME(totalValorPME);
        corretora.setPercenteValorPME(totalValorPME > totalValorPF ? 100 : totalValorPME == 0 ? 0 : 50);

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

    //201808141702 - esert - COR-363 (Web - Model/View - Meus dados)
    @RequestMapping(value = "forcavenda/meus-dados", method = RequestMethod.GET)
    public ModelAndView meusDados(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());
        //return new ModelAndView("forcavenda/editar/home", "forcaVenda", forcaVenda);
        return new ModelAndView("forcavenda/editar/meus_dados", "forcaVenda", forcaVenda);
    }

    //201808141815 - esert - COR-363 (Web - Model/View - Meus dados)
    @RequestMapping(value = "forcavenda/meus-dados-editar", method = RequestMethod.POST)
    public ModelAndView meusDadosEditar(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());
        //return new ModelAndView("forcavenda/editar/editar", "forcaVenda", forcaVenda);
        return new ModelAndView("forcavenda/editar/meus_dados_edicao", "forcaVenda", forcaVenda);
    }

    //201808141820 - esert - COR-363 (Web - Model/View - Meus dados)
    @RequestMapping(value = "forcavenda/editar/salvar", method = RequestMethod.POST)
    public ModelAndView meusDadosSalvar(HttpSession session, @Valid @ModelAttribute("forcaVenda") ForcaVenda forcaVendaParam, BindingResult result) {

        if (result.hasErrors()) {
            //return new ModelAndView("forcavenda/editar/editar", "forcaVenda", forcaVendaParam);
            return new ModelAndView("forcavenda/editar/meus_dados_edicao", "forcaVenda", forcaVendaParam);
        } else {

            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

            ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());
//            forcaVenda.setNome(forcaVendaParam.getNome());
//            forcaVenda.setCelular(forcaVendaParam.getCelular());
//            forcaVenda.setEmail(forcaVendaParam.getEmail());

            ForcaVenda forcaVendaAlterar = new ForcaVenda();
            forcaVendaAlterar.setCdForcaVenda(forcaVenda.getCdForcaVenda());
            forcaVendaAlterar.setNome(forcaVendaParam.getNome());
            forcaVendaAlterar.setCelular(forcaVendaParam.getCelular());
            forcaVendaAlterar.setEmail(forcaVendaParam.getEmail());

            //forcaVendaService.Alterar(forcaVenda); //201808142015 - esert - passa dados de mais quebra no campo status que passa string ao inves de numero
            forcaVendaService.Alterar(forcaVendaAlterar); //201808171216 - esert - COR-591 - pode passar dados de menos que nao quebra mais na chamada dcss pq sera complementado antes no back-end

            //return this.home(session);
            forcaVenda = forcaVendaService.ObterPorDocumento(usuario.getDocumento());
            //return new ModelAndView("forcavenda/editar/home", "forcaVenda", forcaVenda);

            usuario.setEmail(forcaVenda.getEmail());
            session.setAttribute("usuario", usuario);

            return new ModelAndView("forcavenda/editar/meus_dados", "forcaVenda", forcaVenda);
        }
    }

    @RequestMapping(value = "forcavenda/bloqueio", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Login> forcaVendaBloqueio(HttpSession session) {

        try {
            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return forcaVendaService.verificaBloqueio(usuario.getCodigoUsuario());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}