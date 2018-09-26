package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import br.com.odontoprev.portalcorretor.service.dto.CnpjDadosDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaDcms;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpresaService {

    private static final Log log = LogFactory.getLog(EmpresaService.class);

    //201809261534 - esert - COR-827 - alterado para uma prop que ja contenha o caminho do apiGateway /corretorservicos/1.0
    //201809261534 - esert - COR-827 - demais properties foram alteradas para nao conter o caminho do apiGateway /corretorservicos/1.0 em duplicidade
    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.corretoras.empresa}")
    private String dadosEmpresa;

    @Value("${odontoprev.corretoras.empresa.dcms}")
    private String dadosEmpresaDCMS;

    @Value("${odontoprev.corretoras.reenvio.aceite}")
    private String dadosEmpresaAceite;

    @Value("${odontoprev.empresa.email.aceite}")
    private String reenvioEmpresaEmailAceite;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public CnpjDadosAceiteResponse obterDadosReenvio(String cnpj) {
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = null;

        String url = requesBasetUrl + dadosEmpresaAceite + cnpj;
        //String url = "https://172.18.203.20:7001/cnpj-dadosaceite/" + cnpj;
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

        String url = requesBasetUrl + reenvioEmpresaEmailAceite;
        //String url = "http://localhost:8090/empresa-emailaceite";
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

    public CnpjDadosDCMSResponse obterDadosEmpresaDCMS(String cnpj) {
        CnpjDadosDCMSResponse cnpjDadosDCMSResponse = null;

        String url = requesBasetUrl + dadosEmpresa + cnpj;
        //String url = "https://172.18.203.20:7001/cnpj-dados/" + cnpj;
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

    public EmpresaResponse updateDadosEmpresaDCMS(EmpresaDcms empresaDcms) {
        EmpresaResponse empresaResponse = null;

        String url = requesBasetUrl + dadosEmpresaDCMS;
        //String url = "https://172.18.203.20:7001/empresa-dcms";
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

    public EmpresaResponse updateEmpresa(Empresa empresa) {

        log.info("updateEmpresa - ini");
        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/" + "empresa");
        //TODO: Alterar tora para API/API-IT3
        //String url = "http://localhost:8090/empresa";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String stringJson = mapper.writeValueAsString(empresa);

            HttpEntity<String> entity = new HttpEntity<String>(stringJson, headers);

            ResponseEntity<EmpresaResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entity, EmpresaResponse.class);


            log.info("updateEmpresa - fim");
            return retorno.getBody();

        } catch (Exception e) {
            // e.printStackTrace();
            log.info("updateEmpresa - error");
            log.error(e);
            return null;
        }
    }

    //201809260950 - esert - COR-827 : WEB - Criar Service POST /empresa-emailaceite
    public EmpresaResponse envioEmpresaEmailAceite(Empresa empresa) {
        log.info("envioEmpresaEmailAceite - ini");
        EmpresaResponse empresaResponse = null;

        //201809260950 - esert - ATENCAO = A ROTA E A MESMA MAS O VERBO E (((POST)))
        String url = requesBasetUrl + reenvioEmpresaEmailAceite;
        //String url = "http://localhost:8090/empresa-emailaceite";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String strObject = mapper.writeValueAsString(empresa);  //201809261238 - esert - desta empresa sera usado apenas atributo cdEmpresa (Long)

            HttpEntity<String> entity = new HttpEntity<>(strObject, headers);
            ResponseEntity<EmpresaResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, EmpresaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                empresaResponse = retorno.getBody();
                log.info("envioEmpresaEmailAceite - fim ok");
                return empresaResponse;
            } else {
                log.info("envioEmpresaEmailAceite - fim Nao OK");
                return null;
            }

        } catch (Exception e) {
            log.error("envioEmpresaEmailAceite - erro", e);
            return null;
        }
    }

}
