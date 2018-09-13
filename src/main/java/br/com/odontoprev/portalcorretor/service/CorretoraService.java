package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.CorretoraResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CorretoraService {

    private static final Log log = LogFactory.getLog(CorretoraService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.corretoras.DadosCorretora}")
    private String dadosCorretora;

    @Value("${odontoprev.corretoras.salvarEmailCorretora}")
    private String salvarEmailCorretora;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public Corretora obterDadosCorretora(String cnpj) {

        String url = requesBasetUrl + dadosCorretora + cnpj;
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<Corretora> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Corretora.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new Corretora();
            }

        } catch (Exception e) {
            // e.printStackTrace();
            log.error(e);
            return new Corretora();
        }
    }

    public CorretoraResponse salvarEmailCorretora(Corretora corretora) {

        log.info("salvarEmailCorretora - ini");
        log.info(corretora);

        String url = requesBasetUrl + salvarEmailCorretora;
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String stringJson = mapper.writeValueAsString(corretora);

            HttpEntity<String> entity = new HttpEntity<String>(stringJson, headers);

            ResponseEntity<CorretoraResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entity, CorretoraResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("salvarEmailCorretora - fim ok");
                return retorno.getBody();
            } else {
                log.info("salvarEmailCorretora - fim nao-ok");
                log.info("retorno.getStatusCode():[" + retorno.getStatusCode() + "]");
                return null;
            }

        } catch (Exception e) {
            // e.printStackTrace();
            log.info("salvarEmailCorretora - erro");
            log.error(e);
            return null;
        }
    }

    public ResponseEntity<String> gerarContratoCorretora(Long cdCorretora) throws ApiTokenException {
        log.info("GERAR ARQUIVO CONTRATO CORRETORA ->>> gerarArquivoContratoCorretora");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/contratocorretora/" + cdCorretora + "/arquivo");
        //String url = "http://localhost:8090/contratocorretora/" + cdCorretora + "/arquivo";

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

        HttpEntity<Long> entity = new HttpEntity<>(cdCorretora, headers);
        ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (retorno.getStatusCode() == HttpStatus.OK) {
            return retorno;
        } else {
            log.error("GERAR ARQUIVO CONTRATO CORRETORA ->>> gerarArquivoContratoCorretora");
            return null;
        }
    }
}
