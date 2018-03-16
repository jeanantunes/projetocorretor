package br.com.odontoprev.portalcorretor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.odontoprev.portalcorretor.model.VendaPme;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.PessoaJuridica;


@Service
public class CNPJService {  
    
    @Autowired
    private SerasaService serasaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CNPJService.class);

    public VendaPme obterConsultaCNPJ(String cnpj) {                
        try {
        	PessoaJuridica pessoaJuridica = serasaService.consultaSerasaCNPJ(cnpj);
        	//TODO: Converter
        	return new VendaPme();
        } catch (Exception e) {
            LOGGER.error("ERRO AO CONSULTAR SERASA",e);
            return new VendaPme();

        }
    }
}
