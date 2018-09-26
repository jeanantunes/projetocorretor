package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaDcms;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class EmpresaController {

    private static final Log log = LogFactory.getLog(EmpresaController.class);

    @Autowired
    EmpresaService empresaService;


    @RequestMapping(value = "/empresa", method = RequestMethod.PUT)
    public ResponseEntity<EmpresaResponse> updateEmpresa(@RequestBody Empresa empresa) {
        log.info("updateEmpresa - ini");
        log.info(empresa);

        EmpresaResponse response = empresaService.updateEmpresa(empresa);

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        log.info("updateEmpresa - fim");
        return ResponseEntity.ok(response);
    }
}
