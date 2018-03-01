package br.com.odontoprev.portalcorretor.controller.pendente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class AddNovaForcaVendasController {

    @RequestMapping("/corretor/others/forca-de-vendas")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/others/forca-de-vendas","AddNovaForcaVendasModel",new faleConoscoModel());        
		return modelAndView;
    }

}

