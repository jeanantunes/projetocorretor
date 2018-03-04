package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedeCredenciadaService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "https://api.odontoprev.com.br:8243";

    //@Value("${odontoprev.service.dash}")
    private String metodoDash = "/dcms/redecredenciada/1.0";


    public void ObterRedeCredenciada() {

        String url = requesBasetUrl + metodoDash;
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
