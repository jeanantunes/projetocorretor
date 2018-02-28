package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class PlanoSelecionadoController {

    @RequestMapping("/corretor/vendas_pme/Plano_selecionado")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/vendas_pme/Plano_selecionado","EscolhaUmPlanoModel",new faleConoscoModel());        
		return modelAndView;
    }
    
    @RequestMapping(value="/corretor/vendas_pme/Plano_selecionado/salvar",method= RequestMethod.POST)
    public ModelAndView autenticar(@ModelAttribute("login") br.com.odontoprev.portalcorretor.model.Login login) {
      	System.out.println(login.getLogin());
    	System.out.println(login.getSenha());
    	return new ModelAndView("/corretor/vendas_pme/Plano_selecionado");
    }

}
