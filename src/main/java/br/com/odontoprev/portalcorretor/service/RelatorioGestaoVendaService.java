package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.RelatorioGestaoVenda;
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
public class RelatorioGestaoVendaService {

    private static final Log log = LogFactory.getLog(RelatorioGestaoVendaService.class);

    @Value("${odontoprec.service.base}")
    private String requesBasetUrl;

    //TODO: erificar properties
    @Value("${odontoprev.gestaoVenda.csv}")
    private String gestaoVendaCSV;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public RelatorioGestaoVenda gerarCSV(String cnpj) {

        log.info("Relatorio Gestao de Venda - gerarCSV ");

        String url = requesBasetUrl + gestaoVendaCSV + cnpj;
        //String url = "http://localhost:9090/downloadCSV/" + cnpj;

        //RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain"),
                new MediaType("text", "html")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            //headers.setContentType(MediaType.TEXT_PLAIN_VALUE);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<RelatorioGestaoVenda> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, RelatorioGestaoVenda.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new RelatorioGestaoVenda();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new RelatorioGestaoVenda();
        }
    }
}
