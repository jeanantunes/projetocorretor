package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class ConfirmacaoPropostaPmeController {

    @RequestMapping("/corretor/vendas_pme/confirmacao_proposta_pme")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/vendas_pme/confirmacao_proposta_pme","EscolhaUmPlanoModel",new faleConoscoModel());        
		return modelAndView;
    }

}
