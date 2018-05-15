package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpresaService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.corretoras.empresa}")
    private String dadosEmpresa;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public CnpjDadosAceiteResponse obterDadosEmpresa(String cnpj) {
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = null;

        //String url = requesBasetUrl + dadosEmpresa + cnpj;
        String url = "http://localhost:9090/cnpj-dadosaceite/" + cnpj;
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<CnpjDadosAceiteResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, CnpjDadosAceiteResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {

                cnpjDadosAceiteResponse = retorno.getBody();
                return cnpjDadosAceiteResponse;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
