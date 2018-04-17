package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Service
public class PlanoService {

    //@Autowired
    //private ApiManagerTokenService apiManagerTokenService;

    @Value("${odontoprev.PlanosBase.url}")
    private String requestPlanosBase;

    @Value("${odontoprev.Planos.metodo}")
    private String nomeMetodo;

}
