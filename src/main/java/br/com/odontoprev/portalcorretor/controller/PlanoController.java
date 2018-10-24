package br.com.odontoprev.portalcorretor.controller;


import br.com.odontoprev.portalcorretor.model.PlanoInfo;
import br.com.odontoprev.portalcorretor.model.PlanoInfos;
import br.com.odontoprev.portalcorretor.model.PlanoInfosModel;
import br.com.odontoprev.portalcorretor.service.PlanoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class PlanoController {

    private static final Log log = LogFactory.getLog(PlanoController.class);

    @Autowired
    PlanoService planoService;

    @RequestMapping(value = "/info-planos", method = {RequestMethod.GET})
    public ModelAndView getPlanoInfos(Model model) {
        ResponseEntity<PlanoInfos> planoInfos = planoService.getPlanoInfos();

        PlanoInfosModel planoInfosModel = new PlanoInfosModel();
        planoInfosModel.setPf(new ArrayList<>());
        planoInfosModel.setPme(new ArrayList<>());

        if (planoInfos != null) {
            for (PlanoInfo info : planoInfos.getBody().getPlanoInfos()) {
                if (info.getTipoSegmento().equals("PF")) {
                    info.setDescricaoList(Arrays.asList(info.getDescricao().split("\n")));
                    planoInfosModel.getPf().add(info);
                } else if (info.getTipoSegmento().equals("PME")) {
                    info.setDescricaoList(Arrays.asList(info.getDescricao().split("\n")));
                    planoInfosModel.getPme().add(info);
                }
            }
        }

        return new ModelAndView("info-planos", "planoInfosModel", planoInfosModel);
    }
}
