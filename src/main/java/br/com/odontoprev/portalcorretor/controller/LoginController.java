package br.com.odontoprev.portalcorretor.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {

    @RequestMapping("/index")
    public String Index( Model login) {
        return "index";
    }

}
