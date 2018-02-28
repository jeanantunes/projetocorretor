package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(Principal principal) {
        return "".equals(principal.getName()) ? "login" : principal.getName().length() == 11 ? "corretor/homeCorretor" : "corretor/homeCorretora";
    }
/*
    @RequestMapping(value = "/autenticar", method = RequestMethod.POST)
    public ModelAndView autenticar(@ModelAttribute("login") br.com.odontoprev.portalcorretor.model.Usuario login) {
        System.out.println(login.getLogin());
        System.out.println(login.getSenha());
        return new ModelAndView("/corretor/vendas_pme/home");
    }
*/

}
