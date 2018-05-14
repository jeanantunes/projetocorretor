package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.RelatorioGestaoVenda;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public byte[] gerarCSV(String cnpj) {

        log.info("Relatorio Gestao de Venda - gerarCSV ");

        //String url = requesBasetUrl + gestaoVendaCSV + cnpj;
        String url = "http://localhost:9090/downloadCSV/" + cnpj;

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
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                //Path tempFile = Files.createTempFile("relatorio-gestao-vendas",".csv");
                //Files.write(tempFile, retorno.getBody().getBytes());
                //log.info("Download Relatório Geral: " + tempFile);
                //File file = tempFile.toFile();
                //return String.valueOf(new FileSystemResource(file));
                return retorno.getBody().getBytes();
            } else {
                //return String.valueOf(new RelatorioGestaoVenda());
                return new byte[0];
            }

        } catch (Exception e) {
            e.printStackTrace();
            //return String.valueOf(new RelatorioGestaoVenda());
            return new byte[0];
        }
    }
}
