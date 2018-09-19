package br.com.odontoprev.portalcorretor.controller;


import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.model.DadosContratoCorretora;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ContratoCorretoraService;
import br.com.odontoprev.portalcorretor.service.CorretoraService;
import br.com.odontoprev.portalcorretor.service.dto.ArquivoContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretoraPreenchido;
import javassist.bytecode.ByteArray;
import java.util.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Controller
@RestController
public class ContratoCorretoraController {

    @Autowired
    CorretoraService corretoraService;

    private static final Log log = LogFactory.getLog(CorretoraController.class);

    @Autowired
    ContratoCorretoraService contratoCorretoraService;

    @RequestMapping(value = "/corretora/contrato", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {

        try{

            Object acessoValido = session.getAttribute("acessouModalContrato");

            if (acessoValido == null) {
                String redirectUrl = "corretora/home";
                return new ModelAndView("redirect:" + redirectUrl);
            }

            session.removeAttribute("acessouModalContrato");

            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
            String codSusep = (String) session.getAttribute("codSusep");
            Boolean contratoAceito;

            DadosContratoCorretora dadosContratoCorretora = new DadosContratoCorretora();
            dadosContratoCorretora.setCodSusep(codSusep);
            dadosContratoCorretora.setCdCorretora((long) usuario.getCodigoCorretora());

            if (usuario.getDtAceiteContrato() != null) {

                contratoAceito = true;
                dadosContratoCorretora.setContratoAceito(contratoAceito);
                return new ModelAndView("corretora/contrato", "contratoAceito", dadosContratoCorretora);

            }

            ContratoCorretoraPreenchido contratoCorretoraPreenchido = contratoCorretoraService.obterModeloContrato(dadosContratoCorretora);

            contratoAceito = false;
            dadosContratoCorretora.setContratoAceito(contratoAceito);
            dadosContratoCorretora.setTextoContrato(contratoCorretoraPreenchido.getContratoPreenchido());

            return new ModelAndView("corretora/contrato", "contratoAceito", dadosContratoCorretora);

        }catch (Exception e){

            log.info("Erro ao carregar contrato - erro");
            log.error(e);
            return new ModelAndView("corretora/contrato");

        }
    }

    @RequestMapping(value = "/corretora/aceitarcontrato", method = RequestMethod.POST)
    public ResponseEntity postContratoCorretora(HttpSession session) {

        try {

            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
            String codSusep = (String) session.getAttribute("codSusep");

            DadosContratoCorretora dadosContratoCorretora = new DadosContratoCorretora();
            dadosContratoCorretora.setCodSusep(codSusep);
            dadosContratoCorretora.setCdCorretora((long) usuario.getCodigoCorretora());

            ContratoCorretora contratoCorretora = contratoCorretoraService.postContratoCorretora(dadosContratoCorretora);

            if (contratoCorretora == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            usuario.setDtAceiteContrato(contratoCorretora.getDtAceiteContrato());
            session.setAttribute("usuario", usuario);
            session.setAttribute("acessouAceiteContrato", true);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {

            log.info("salvarContrato - erro");
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(value = "/corretora/contrato-aceito", method = RequestMethod.GET)
    public ModelAndView contratoAceito(HttpSession session) {

        Object acessoValido = session.getAttribute("acessouAceiteContrato");

        if (acessoValido == null) {
            String redirectUrl = "corretora/home";
            return new ModelAndView("redirect:" + redirectUrl);
        }

        session.removeAttribute("acessouAceiteContrato");

        return new ModelAndView("corretora/contrato-aceito");
    }

    @RequestMapping(value = "/downloadcontratocorretora", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ContratoCorretoraPreenchido> downloadContratoCorretora2(@RequestParam("cdCorretora") Long cdCorretora, HttpSession session){

        try
        {
            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

            if (usuario.getDtAceiteContrato() == null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return corretoraService.gerarContratoCorretora(cdCorretora);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
