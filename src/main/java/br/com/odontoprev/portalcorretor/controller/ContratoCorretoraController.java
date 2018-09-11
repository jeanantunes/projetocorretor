package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Cadastro;
import br.com.odontoprev.portalcorretor.model.ContratoCorretora;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpSession;

@Controller
@RestController
public class ContratoCorretoraController {

    private static final Log log = LogFactory.getLog(CorretoraController.class);

    @RequestMapping(value = "/corretora/contrato", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session){

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        Long codSusep = (Long) session.getAttribute("codSusep");


        Boolean contratoAceito;

        if (usuario.getDtAceiteContrato() != null){

            contratoAceito = true;
            return new ModelAndView("/contrato/contrato", "contratoAceito", contratoAceito);

        }

        contratoAceito = false;
        return new ModelAndView("/corretora/contrato", "contratoAceito", contratoAceito);
    }

}
