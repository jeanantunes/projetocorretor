package br.com.odontoprev.portalcorretor.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;

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
    
    //201809261030 - esert - COR-826 : WEB - Criar Controller POST /empresa-emailaceite
    @RequestMapping(value = "/empresa-emailaceite", method = RequestMethod.POST)
    public ResponseEntity<EmpresaResponse> postEmpresaEmailAceite(@RequestBody Empresa empresa) {
    	log.info("postEmpresaEmailAceite - ini");
    	log.info(empresa);
    	
    	EmpresaResponse response = empresaService.envioEmpresaEmailAceite(empresa);
    	
    	if (response == null) {
    		return ResponseEntity.noContent().build();
    	}
    	
    	log.info("postEmpresaEmailAceite - fim");
    	return ResponseEntity.ok(response);
    }
}
