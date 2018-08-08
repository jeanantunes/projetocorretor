package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.service.EmailAceiteService;
import br.com.odontoprev.portalcorretor.service.dto.EmailAceite;
import br.com.odontoprev.portalcorretor.service.dto.TokenAceite;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class EmailAceiteController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedeCredenciadaController.class);

    @Autowired
    EmailAceiteService emailAceiteService;

    @RequestMapping(value = "/termoAceite/{token}", method = RequestMethod.GET)
    public ModelAndView confirmarEmailAceite(@PathVariable String token, HttpServletRequest request) {

        LOGGER.info("###### confirmarEmailAceite ######");

        TokenAceite tokenAceite = emailAceiteService.ObterDadosTokenAceite(token);
        //model.addAttribute("tokenAceite", tokenAceite);

        String ip = capturarIP(request);

        if (tokenAceite.getId() == 200) {
            confirmaEmailAceite(ip, tokenAceite);
        }

        return new ModelAndView("email", "tokenAceite", tokenAceite);
    }

    private void confirmaEmailAceite(String ip, TokenAceite tokenAceite) {
        EmailAceite aceite = new EmailAceite();
        aceite.setCdVenda(tokenAceite.getCdVenda());
        aceite.setToken(tokenAceite.getToken());
        aceite.setEmail(tokenAceite.getEmail());
        aceite.setIp(ip);
        aceite.setDataAceite(new Date());

        emailAceiteService.confirmarEmailAceite(aceite);
    }

    private String capturarIP(HttpServletRequest request) {

        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        LOGGER.info("ipAddress: " + ipAddress);

        LOGGER.info("getRemoteAddr: " + request.getRemoteAddr());

        return ipAddress;
    }
}
