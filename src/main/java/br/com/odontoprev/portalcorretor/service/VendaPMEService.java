package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.service.dto.VendaPMERequest;
import br.com.odontoprev.portalcorretor.service.dto.VendaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VendaPMEService {
    private static final Log log = LogFactory.getLog(VendaPMEService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.vendaPME.metodo}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public long vender(VendaPMERequest request) {
        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", metodo);
        RestTemplate restTemplate = new RestTemplate();
        VendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String object = mapper.writeValueAsString(request);
            
            log.info("JSON: \n"+new Gson().toJson(request));

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<VendaResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, VendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                result = retorno.getBody();
                log.info("Venda realizada " + result.getId()+" "+result.getMensagem());
                return result.getId();
            } else {
                log.info("Erro ao realizar a venda " + result.getMensagem());
                return 0;
            }

        } catch (Exception e) {            
            log.error("Erro ao realizar uma venda",e);
            return 0;
        }

    }

}
