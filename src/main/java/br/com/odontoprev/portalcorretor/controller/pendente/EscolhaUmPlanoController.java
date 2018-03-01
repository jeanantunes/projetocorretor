package br.com.odontoprev.portalcorretor.controller.pendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class EscolhaUmPlanoController {

    @RequestMapping("/corretor/vendas_pme/Escolha_um_plano")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/vendas_pme/Escolha_um_plano","EscolhaUmPlanoModel",new faleConoscoModel());        
		return modelAndView;
    }
}
