package br.com.odontoprev.portalcorretor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import br.com.odontoprev.portalcorretor.service.dto.CnpjDadosDCMSResponse;

@Service
public class EmpresaService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.corretoras.empresa}") // /cnpj-dados/
    private String dadosEmpresa;

    @Value("${odontoprev.corretoras.reenvio.aceite}") // /cnpj-dadosaceite/
    private String dadosEmpresaAceite;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    // /cnpj-dadosaceite/
    public CnpjDadosAceiteResponse obterDadosReenvio(String cnpj) {
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = null;

        String url = requesBasetUrl + dadosEmpresaAceite + cnpj; // /cnpj-dadosaceite/
        //String url = "http://localhost:9090/cnpj-dadosaceite/" + cnpj;
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

    // /cnpj-dados/
    //201805161145 - esert - COR-170
    public CnpjDadosDCMSResponse obterDadosEmpresaDCMS(String cnpj) {
        CnpjDadosDCMSResponse cnpjDadosDCMSResponse = null;

        String url = requesBasetUrl + dadosEmpresa + cnpj; // /cnpj-dados/
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<CnpjDadosDCMSResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, CnpjDadosDCMSResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {

                cnpjDadosDCMSResponse = retorno.getBody();
                return cnpjDadosDCMSResponse;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
