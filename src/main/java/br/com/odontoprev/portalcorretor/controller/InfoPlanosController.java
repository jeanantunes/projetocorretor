package br.com.odontoprev.portalcorretor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoPlanosController {
    @RequestMapping("/info-planos")
    public String indexInfoPlano() {
        return "info-planos";

    }

    @RequestMapping("/info-planos-integral-doc-le")
    public String indexInfoPlanoIntegralDocLe() {
        return "info-planos-integral-doc-le";

    }

    @RequestMapping("/info-planos-master-le")
    public String indexInfoPlanoMasterLe() {
        return "info-planos-master-le";

    }


}
