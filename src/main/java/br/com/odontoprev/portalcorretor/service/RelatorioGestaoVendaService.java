package br.com.odontoprev.portalcorretor.service;

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

    @Value("${odontoprev.relatorio.xls.pme}")
    private String relatorioCsvPME;

    @Value("${odontoprev.relatorio.xls.pf}")
    private String relatorioCsvPF;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public byte[] gerarCsvPME(String dataInicio, String dataFim, String cnpj) {

        log.info("Relatorio Gestao de Venda PME - gerarCSV ");

        //String url = requesBasetUrl + relatorioCsvPME + dataInicio + "/" + dataFim + "/" + cnpj;
        String url = "http://localhost:8090/downloadxls/corretoratotalvidaspme/" + dataInicio + "/" + dataFim + "/" + cnpj;

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain"),
                new MediaType("text", "html")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody().getBytes();
            } else {
                return new byte[0];
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }


    public byte[] gerarCsvPF(String dataInicio, String dataFim, String cnpj) {

        log.info("Relatorio Gestao de Venda PF - gerarCSV ");

        //TODO: Retornar para IT-3
        String url = requesBasetUrl + relatorioCsvPF + dataInicio + "/" + dataFim + "/" + cnpj;
        //String url = "http://localhost:8090/downloadxls/corretoratotalvidaspf/" + dataInicio + "/" + dataFim + "/" + cnpj;

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain"),
                new MediaType("text", "html")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody().getBytes();
            } else {
                return new byte[0];
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
