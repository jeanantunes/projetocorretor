package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RedeCredenciadaForcaVendasController {

    @RequestMapping("/rede-credenciada-forca-vendas")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/others/busca-rede-credenciadas-forca-vendas","login", new UsuarioSession());
        return modelAndView;
    }

}
