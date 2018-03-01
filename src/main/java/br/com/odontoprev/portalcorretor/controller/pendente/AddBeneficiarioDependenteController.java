package br.com.odontoprev.portalcorretor.controller.pendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class AddBeneficiarioDependenteController {

    @RequestMapping("/corretor/vendas_pme/Add_beneficiario_dependente")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/vendas_pme/Add_beneficiario_dependente","EscolhaUmPlanoModel",new faleConoscoModel());        
		return modelAndView;
    }

}
