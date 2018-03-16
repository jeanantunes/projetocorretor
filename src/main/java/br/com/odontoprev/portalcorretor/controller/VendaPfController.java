package br.com.odontoprev.portalcorretor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.odontoprev.portalcorretor.exceptions.SerasaConsultaException;
import br.com.odontoprev.portalcorretor.model.*;
import br.com.odontoprev.portalcorretor.service.SerasaService;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.PessoaJuridica;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static br.com.odontoprev.portalcorretor.service.dto.Plano.*;

@Controller
public class VendaPfController {

    private static final String URL_ESTADO = "https://api.odontoprev.com.br:8243/cep/1.1/estados";


    @RequestMapping(value = "venda/pf/Escolha_um_plano")
    public ModelAndView escolhaPlanoPf(HttpSession session) throws IOException, SerasaConsultaException {

        Carrinho carrinho = new Carrinho();
        session.setAttribute("carrinho", carrinho);
        return new ModelAndView("venda/pf/escolhaPlanoPF", "carrinho", carrinho);
    }


    @RequestMapping(value = "venda/pf/Escolha_um_plano/addPlanoSelecionadoPf/{nomePlano}/{modPagamento}/{ehCarencia}")
    public ModelAndView planoSelecionadoPf(@PathVariable("nomePlano") String nomePlano,
                                           @PathVariable("modPagamento") String modPagamento,
                                           @PathVariable("ehCarencia") String ehCarencia,
                                           HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        //TODO Refatorar
        if (nomePlano.equals(Dental_Bem_Estar.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("ANUAL")) {
            carrinho.getPlanos().add(Dental_Bem_Estar_Anual_CC);
        }else if(nomePlano.equals(Dental_Bem_Estar.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Bem_Estar_Anual_SC);
        }else if(nomePlano.equals(Dental_Bem_Estar.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("MENSAL")){
            carrinho.getPlanos().add(Dental_Bem_Estar);
        }else if(nomePlano.equals(Dente_De_Leite.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dente_De_Leite_Anual);
        }else if(nomePlano.equals(Dente_De_Leite.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("MENSAL")){
            carrinho.getPlanos().add(Dente_De_Leite);
        }else if(nomePlano.equals(Dental_Estetica.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Estetica_Anual_CC);
        }else if(nomePlano.equals(Dental_Estetica.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Estetica_Anual_SC);
        }else if(nomePlano.equals(Dental_Estetica.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("MENSAL")){
            carrinho.getPlanos().add(Dental_Estetica);
        }else if(nomePlano.equals(Dental_Orto.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Orto_Anual_CC);
        }else if(nomePlano.equals(Dental_Orto.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Orto_Anual_SC);
        }else if(nomePlano.equals(Dental_Orto.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("MENSAL")){
            carrinho.getPlanos().add(Dental_Orto);
        }else if(nomePlano.equals(Dental_Vip.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Vip_Anual_CC);
        }else if(nomePlano.equals(Dental_Vip.getCdPlano()) && ehCarencia.equals("N") && modPagamento.equals("ANUAL")){
            carrinho.getPlanos().add(Dental_Vip_Anual_SC);
        }else if(nomePlano.equals(Dental_Vip.getCdPlano()) && ehCarencia.equals("S") && modPagamento.equals("MENSAL")){
            carrinho.getPlanos().add(Dental_Vip);
        }

        return new ModelAndView("venda/pf/titularDoPlanoPF", "carrinho", carrinho);
    }

    @RequestMapping(value = "venda/pf/Escolha_um_plano/deletePlanoSelecionadoPf")
    public void removePlano(HttpSession session) throws IOException {
     //escolhaPlanoPf(session);
    }

    @RequestMapping(value = "venda/pf/cnpj", method = RequestMethod.GET)
    public ResponseEntity<Cadastro> cnpj(@RequestParam("cnpj") String cnpj) {

        Cadastro cadastro = new Cadastro();
        cadastro.setCnpj(cnpj);
        cadastro.setRazaoSocial("OdontoPrev");
        cadastro.setCnae("123-12");
        cadastro.setSimplesNacional("Não");
        cadastro.setDataAbertura("01/01/2001");
        //cadastro.setStatusCnpj("VALIDO");
        cadastro.setNomeRepresentanteLegal("Representante Legal");
        cadastro.setCpfRepresentanteLegal("123.123.123-34");
        cadastro.setTelefone("(11)2222-1234");
        cadastro.setCelular("(11)98765-1234");
        cadastro.setEmail("teste@odontoprev.com.br");
        return cnpj.equals("23.423.423/423") ? ResponseEntity.ok(cadastro) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "venda/pf/pagamentoDebitoCC", method = RequestMethod.POST)
    public ModelAndView pagamentoDebito() {

        VendaPf vendaPf = new VendaPf();
        vendaPf.setPagamentoCartaoNome("0043");
        vendaPf.setPagamentoCartaoValidade("2018");

        return new ModelAndView("venda/pf/pagamentoDebitoCC", "vendaPf", vendaPf);
    }

    @RequestMapping(value = "venda/pf/compraRealizadaPF", method = RequestMethod.GET)
    public ModelAndView compraRealizada(@PathVariable("cadastro") VendaPf idVenda) {
        VendaPf vendaPf = new VendaPf();
        return new ModelAndView("venda/pf/compraRealizadaPF", "vendaPf", vendaPf);
    }

    @RequestMapping(value = "venda/pf/cep", method = RequestMethod.GET)
    public ModelAndView cep(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("cadastro/home", "cadastro", cadastro);
    }

    @RequestMapping(value = "venda/pf/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/cadastrar/termosCondicoes", "cadastro", cadastro);
    }

    @RequestMapping(value = "venda/pf/termosCondicoes", method = RequestMethod.POST)
    public ModelAndView termosCondicoes(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/bemvindo", "cadastro", cadastro);
    }

    @RequestMapping(value = "venda/pf/bemvindo", method = RequestMethod.POST)
    public ModelAndView bemvindo(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/bemvindo", "cadastro", cadastro);
    }

}
