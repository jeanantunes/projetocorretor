package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import br.com.odontoprev.portalcorretor.service.dto.CnpjDadosDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaDcms;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpresaService {

    @Value("${odontoprec.service.base}")
    private String requesBasetUrl;

    @Value("${odontoprev.corretoras.empresa}") // /cnpj-dados/
    private String dadosEmpresa;

    @Value("${odontoprev.corretoras.empresa.dcms}") // /empresa-dcms
    private String dadosEmpresaDCMS;

    @Value("${odontoprev.corretoras.reenvio.aceite}") // /cnpj-dadosaceite/
    private String dadosEmpresaAceite;

    @Value("${odontoprev.corretoras.email.aceite}")
    private String reenvioEmailAceite;

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

    public EmpresaResponse reenvioEmailAceite(CnpjDadosAceiteResponse cnpjDadosAceiteResponse) {
        EmpresaResponse empresaResponse = null;

        cnpjDadosAceiteResponse.getCnpj().replace(".", "").replace("/", "").replace("-", "");
        String url = requesBasetUrl + dadosEmpresaAceite; // /cnpj-dadosaceite/
        //String url = "http://localhost:9090/empresa-emailaceite";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String strObject = mapper.writeValueAsString(cnpjDadosAceiteResponse);

            HttpEntity<String> entity = new HttpEntity<>(strObject, headers);
            ResponseEntity<EmpresaResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entity, EmpresaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {

                empresaResponse = retorno.getBody();
                return empresaResponse;
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

        //String url = requesBasetUrl + dadosEmpresa + cnpj; // /cnpj-dados/
        String url = "http://localhost:9090/cnpj-dados/" + cnpj;
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

    // /cnpj-dados/
    //201805161145 - esert - COR-170

    //201805171816 - esert - COR-170
    public EmpresaResponse updateDadosEmpresaDCMS(EmpresaDcms empresaDcms) {
        EmpresaResponse empresaResponse = null;

        //String url = requesBasetUrl + dadosEmpresaDCMS; // /empresa-dcms/ PUT
        String url = "http://localhost:9090/empresa-dcms";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String jsonAsString = mapper.writeValueAsString(empresaDcms);

            HttpEntity<String> entityReq = new HttpEntity<>(jsonAsString, headers);
            ResponseEntity<EmpresaResponse> response = restTemplate.exchange(url, HttpMethod.PUT, entityReq, EmpresaResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                empresaResponse = response.getBody();
                return empresaResponse;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
