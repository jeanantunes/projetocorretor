package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.Arquivo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArquivoService {

    private static final Log log = LogFactory.getLog(ArquivoService.class);

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.corretor.api.contexto.api.url}")
    private String contexto;

    @Value("${odontoprev.plano.info.arquivo}")
    private String arquivoPlanoInfo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public Arquivo getArquivo(Long cdArquivo) {
        log.info("GET ARQUIVO ->>> getArquivo");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", contexto + arquivoPlanoInfo);

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<Arquivo> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Arquivo.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new Arquivo();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Arquivo();
        }
    }
}
