package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Cadastro;
import br.com.odontoprev.portalcorretor.model.ContratoCorretora;
import br.com.odontoprev.portalcorretor.model.DadosContratoCorretora;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ContratoCorretoraService;
import br.com.odontoprev.portalcorretor.service.CorretoraService;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretoraPreenchido;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ContratoCorretoraController {

    private static final Log log = LogFactory.getLog(CorretoraController.class);

    @Autowired
    ContratoCorretoraService contratoCorretoraService;

    @RequestMapping(value = "/corretora/contrato", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session){

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        String codSusep = (String) session.getAttribute("codSusep");
        Boolean contratoAceito;

        DadosContratoCorretora dadosContratoCorretora = new DadosContratoCorretora();
        dadosContratoCorretora.setCodSusep(codSusep);
        dadosContratoCorretora.setCdCorretora( (long) usuario.getCodigoCorretora());

        if (usuario.getDtAceiteContrato() != null){

            contratoAceito = true;
            dadosContratoCorretora.setContratoAceito(contratoAceito);
            return new ModelAndView("/corretora/contrato", "contratoAceito", dadosContratoCorretora);

        }

        ContratoCorretoraPreenchido contratoCorretoraPreenchido = contratoCorretoraService.obterModeloContrato(dadosContratoCorretora);

        contratoAceito = false;
        dadosContratoCorretora.setContratoAceito(contratoAceito);
        dadosContratoCorretora.setTextoContrato(contratoCorretoraPreenchido.getContratoPreenchido());
        return new ModelAndView("/corretora/contrato", "contratoAceito", dadosContratoCorretora);
    }

}
