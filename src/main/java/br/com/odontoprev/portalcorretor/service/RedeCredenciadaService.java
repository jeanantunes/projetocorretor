package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedeCredenciadaService {
    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.redecredenciada.metodo}")
    private String redecredenciada_metodoDash;


    public void ObterRedeCredenciada() {

        String url = requesBasetUrl + redecredenciada_metodoDash;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
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
