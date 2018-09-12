package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.model.DadosContratoCorretora;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ContratoCorretoraService;
import br.com.odontoprev.portalcorretor.service.CorretoraService;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretoraPreenchido;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        String codSusep = (String) session.getAttribute("codSusep");
        Boolean contratoAceito;

        DadosContratoCorretora dadosContratoCorretora = new DadosContratoCorretora();
        dadosContratoCorretora.setCodSusep(codSusep);
        dadosContratoCorretora.setCdCorretora((long) usuario.getCodigoCorretora());

        if (usuario.getDtAceiteContrato() != null) {

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

    @RequestMapping(value = "/downloadContratoCorretora", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView downloadContratoCorretora(Model model, HttpServletResponse
            response, @RequestParam("cdCorretora") Long cdCorretora) throws IOException, ApiTokenException {

        ResponseEntity<String> file = corretoraService.gerarContratoCorretora(cdCorretora);

        if (file == null) {
            downloadContratoCorretora(model, response, cdCorretora);
            model.addAttribute("error", "O arquivo não foi encontrado em nosso sistema.");
            return new ModelAndView("meus_dados", "meus_dados", file);
        } else {
            String fileName = file.getHeaders().get("Content-Disposition").get(0).split(";")[1].split("=")[1];

            response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(headerKey, headerValue);
            response.getWriter().write(file.toString());
            response.getWriter().flush();
        }

        downloadContratoCorretora(model, response, cdCorretora);
        return new ModelAndView("meus_dados", "meus_dados", file);
    }

}
