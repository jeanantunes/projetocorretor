package br.com.odontoprev.portalcorretor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import br.com.odontoprev.portalcorretor.service.PropertieService;

@Controller
@RestController
public class PropertieController {

    @Autowired
    PropertieService propertieService;

    //201808092006 - esert - COR-413:(Implementar Propertie util para o front)
    @RequestMapping(value = "propertie", method = RequestMethod.GET)
    public ResponseEntity getPropertie(@RequestParam("key") String propertieName, HttpSession session) {
        //UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        String propertieValue = propertieService.getPropertie(propertieName);

        if(propertieValue == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ModelMap(propertieName, propertieValue));
    }

}
