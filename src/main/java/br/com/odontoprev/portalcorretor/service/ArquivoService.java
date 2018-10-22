package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.PlanoInfos;
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
public class ArquivoService {

    private static final Log log = LogFactory.getLog(ArquivoService.class);

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.detalhes.proposta}")
    private String metodo;

    @Value("${odontoprev.plano.info}")
    private String planoinfo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public ResponseEntity<String> getArquivo(Long cdArquivo) {
        log.info("GET ARQUIVO ->>> getArquivo");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/arquivo/" + cdArquivo);

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

            HttpEntity<Long> entity = new HttpEntity<>(cdArquivo, headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PlanoInfos getPlanoInfos() {

        log.info("getPlanoInfos ->>> Lista");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/" + planoinfo);

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain"),
                new MediaType("text", "html")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>(planoinfo, headers);
            ResponseEntity<PlanoInfos> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, PlanoInfos.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new PlanoInfos();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new PlanoInfos();
        }
    }
}
