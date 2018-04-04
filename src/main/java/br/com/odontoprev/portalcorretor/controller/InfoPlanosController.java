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

    @RequestMapping("/info-planos-rol-min")
    public String indexInfoPlanoRolMin() {
        return "info-planos-rol-min";

    }


    @RequestMapping("/info-planos-dental-bem-estar")
    public String indexInfoPlanoDentalBemEstar() {
        return "info-planos-dental-bem-estar";

    }

    @RequestMapping("/info-planos-dental-vip")
    public String indexInfoPlanoDentalVip() {
        return "info-planos-dental-vip";

    }
    @RequestMapping("/info-planos-dental-orto")
    public String indexInfoPlanoDentalOrto() {
        return "info-planos-dental-orto";

    }

    @RequestMapping("/info-planos-dental-estetica")
    public String indexInfoPlanoDentalEstetica() {
        return "info-planos-dental-estetica";

    }
    
    @RequestMapping("/info-planos-PF")
    public String indexInfoPlanoPF() {
        return "info-planos-PF";

    }
    
    @RequestMapping("/info-planos-PME")
    public String indexInfoPlanoPME() {
        return "info-planos-PME";

    }
    
    @RequestMapping("/email")
    public String email() {
        return "email";
    }
    
    
    
   
    
    
    
    
}
