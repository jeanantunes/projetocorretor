package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    @RequestMapping(value="/autenticar",method= RequestMethod.POST)
    public String autenticar(@ModelAttribute("login") br.com.odontoprev.portalcorretor.model.login login) {
      	System.out.println(login.getLogin());
    	System.out.println(login.getSenha());
    	return "autenticado";
    }

}
