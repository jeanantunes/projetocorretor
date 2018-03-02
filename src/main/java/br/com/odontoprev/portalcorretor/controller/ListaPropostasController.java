package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListaPropostasController {

    @RequestMapping(value = "/lista-propostas", method = RequestMethod.GET)
    public ModelAndView ListaPropostas(){
        return new ModelAndView("/lista-propostas");
    }
}
