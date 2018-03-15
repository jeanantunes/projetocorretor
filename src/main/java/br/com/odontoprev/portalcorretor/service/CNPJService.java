package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.controller.VendaPmeController;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CNPJService {
//    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "https://api.odontoprev.com.br:8243/";

  //  @Value("${odontoprev.service.serasa.CNPJ}")
    private String serasaCnpj = "serasa/consulta/1.0/";

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public VendaPme obterConsultaCNPJ(String cnpj) {
        apiManagerTokenService = new ApiManagerTokenService();
        String url = requesBasetUrl + serasaCnpj + cnpj;
        RestTemplate restTemplate = new RestTemplate();
        VendaPmeController vendaPmeController = new VendaPmeController();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + vendaPmeController.obterToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<VendaPme> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, VendaPme.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new VendaPme();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new VendaPme();

        }
    }
}
