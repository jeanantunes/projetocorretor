package br.com.odontoprev.portalcorretor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.odontoprev.portalcorretor.service.PropertieService;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class PropertieController {

    @Autowired
    PropertieService propertieService;

    //201808092006 - esert - COR-413:(Implementar Propertie util para o front)
    @RequestMapping(value = "propertie/{propertieName}", method = RequestMethod.GET)
    public ModelMap getPropertie(@PathVariable String propertieName, HttpSession session) {
        //UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        String propertieValue = propertieService.getPropertie(propertieName);

        return new ModelMap(propertieName, propertieValue);
    }

}
