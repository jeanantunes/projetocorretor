package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.faleConoscoModel;

@Controller
public class FaleConoscoController {

    @RequestMapping("/corretor/others/fale-conosco")
    public ModelAndView index() {
    	
        ModelAndView modelAndView = new ModelAndView("/corretor/others/fale-conosco","faleConoscoModel",new faleConoscoModel());        
		return modelAndView;
    }
}
