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
public class PlanoService {


    private static final Log log = LogFactory.getLog(PlanoService.class);

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.corretor.api.contexto.api.url}")
    private String contexto;

    @Value("${odontoprev.plano.info}")
    private String planoinfo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public ResponseEntity<PlanoInfos> getPlanoInfos() {

        log.info("getPlanoInfos ->>> Lista");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", contexto + planoinfo);

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

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao consultar Planos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
