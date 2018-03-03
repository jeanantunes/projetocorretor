package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FaleConoscoController {
    @RequestMapping("/fale-conosco")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("fale-conosco");
        return modelAndView;
    }
}
