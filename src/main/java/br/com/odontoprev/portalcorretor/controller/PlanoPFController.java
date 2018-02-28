/**
 * 
 */
package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.Cadastro;

@Controller
public class PlanoPFController {
	@RequestMapping(value = "/escolhaplanopf", method = RequestMethod.POST)
	public ModelAndView escolhaPlanoPF(@ModelAttribute("escolhaplanopf") Cadastro cadastro) {
		return new ModelAndView("cadastro/bemvindo", "cadastro", cadastro);
	}

}
