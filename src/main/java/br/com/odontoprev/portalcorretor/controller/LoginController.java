package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(Principal principal) {
        return "".equals(principal.getName()) ? "login" : principal.getName().length() == 11 ? "forcavenda/home" : "corretora/home";
    }

    @RequestMapping("/logout")
    public String logout(Principal principal) {
        //TODO: mamar session e etc
        return "/login";
    }
}
