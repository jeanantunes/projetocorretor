package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CorretorPMEController {

    @RequestMapping(value = "/corretor/homeCorretora", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("perfil");



        return new ModelAndView();
    }

}
