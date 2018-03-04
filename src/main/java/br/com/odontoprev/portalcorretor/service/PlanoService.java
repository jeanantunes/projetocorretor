package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlanoService {

    //@Value("${odontoprev.PlanosBasePME.url}")
    private String requestPlanosBasePME = "https://api.odontoprev.com.br:8243/dcms/empresa/2.0";

    //@Value("${odontoprev.PlanosBasePF.url}")
    private String requestPlanosBasePF = "https://api.odontoprev.com.br:8243/dcms/empresa/2.0";

    //@Value("${odontoprev.Planos.metodo}")
    private String nomeMetodo = "/planos";


    public void ObterPlanosPME()
    {
        String url = requestPlanosBasePME + nomeMetodo;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                //return retorno.getBody();
            } else {
                //
            }

        } catch (Exception e) {
            e.printStackTrace();
            //return new DashboardPropostas();
        }
    }

    public void ObterPlanoPF()
    {
        String url = requestPlanosBasePF + nomeMetodo;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                //return retorno.getBody();
            } else {
                //
            }

        } catch (Exception e) {
            e.printStackTrace();
            //return new DashboardPropostas();
        }
    }
}
