package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CorretorPF {

    @RequestMapping(value = "/corretor/homeCorretor", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView();
    }

}
