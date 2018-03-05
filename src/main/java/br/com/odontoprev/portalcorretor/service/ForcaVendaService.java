package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVendaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForcaVendaService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;// = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

    private String metodoGetPorDocuemnto_Post_Put = "forcavenda/";

    private String metodoListaPorCorretora = "";

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public long Criar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String object = mapper.writeValueAsString(forcaVenda);

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                result = retorno.getBody();
                return result.getId();
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean Alterar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entity, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<ForcaVenda> ObterListaPorCorretora(long codigoCorretora) {
        String url = requesBasetUrl + metodoListaPorCorretora + codigoCorretora;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ForcaVenda[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda[].class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(retorno.getBody());
            } else {
                return Arrays.asList(new ForcaVenda[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.asList(new ForcaVenda[0]);
        }
    }

    public ForcaVenda ObterPorDocumento(String documento) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put + documento;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ForcaVenda> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new ForcaVenda();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ForcaVenda();
        }
    }
}
