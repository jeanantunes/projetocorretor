package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.MateriaisDivulgacao;
import br.com.odontoprev.portalcorretor.model.MateriaisDivulgacaoResponse;
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
public class MateriaisDivulgacaoService {

    @Value("${odontoprec.service.base}")
    private String requesBasetUrl;

    @Value("${odontoprev.materiais.divulgacao}")
    private String materiais;

    @Value("${odontoprev.materiais.download}")
    private String download;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    final Log log = LogFactory.getLog(MateriaisDivulgacaoResponse.class);

    public MateriaisDivulgacaoResponse materiaisDivulgacao() {

        log.info("Materiais Divulgacao ->>> Lista");

        //String url = requesBasetUrl + materiais;
        String url = "http://localhost:8090/materiaisdivulgacao/web";

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain"),
                new MediaType("text", "html")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>(materiais, headers);
            ResponseEntity<MateriaisDivulgacaoResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, MateriaisDivulgacaoResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new MateriaisDivulgacaoResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new MateriaisDivulgacaoResponse();
        }
    }


    public MateriaisDivulgacao arquivoDownload(Long id) {

        log.info("Materiais Divulgacao ->>> Download");

        //String url = requesBasetUrl + download + id;
        //String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/" + download + id);
        String url = "http://localhost:8090/materialdivulgacao/" + id;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(materiais, headers);
            ResponseEntity<MateriaisDivulgacao> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, MateriaisDivulgacao.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new MateriaisDivulgacao();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new MateriaisDivulgacao();
        }
    }
}
