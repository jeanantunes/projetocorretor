package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.DetalhesBoletoResponse;
import br.com.odontoprev.portalcorretor.model.DetalhesPropostaResponse;
import br.com.odontoprev.portalcorretor.model.FichaFinanceiraResponse;
import br.com.odontoprev.portalcorretor.model.FichaFinancieraBoleto;
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
public class PropostaService {

    private static final Log log = LogFactory.getLog(PropostaService.class);

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.detalhes.proposta}")
    private String metodo;

    @Value("${odontoprev.detalhes.proposta.boleto}")
    private String boleto;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public DetalhesPropostaResponse detalhesProposta(String cdVenda) {
        DetalhesPropostaResponse detalhesPropostaResponse = null;

        String url = requestBase + metodo + cdVenda;
        //String url = "http://localhost:9090/proposta/dados/critica/venda/" + cdVenda;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<DetalhesPropostaResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DetalhesPropostaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("DetalhesPropostaService ->>> " + retorno.getStatusCode());
                detalhesPropostaResponse = retorno.getBody();
                return detalhesPropostaResponse;
            } else {
                log.error("DetalhesPropostaService ->>> " + retorno.getStatusCode() + "\n" + retorno.getBody());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("DetalhesPropostaService ->>> " + e);
            return null;
        }
    }

    public FichaFinanceiraResponse listarBoleto(DetalhesBoletoResponse detalhesBoletoResponse) {
        FichaFinanceiraResponse financeiraResponse = null;

        log.info("LISTAR BOLETO ->>> detalhesBoleto");

        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/obterfichafinanceira/codigoassociado
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/obterfichafinanceira/numeroproposta
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/gerarboleto
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/gerarboletofile

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token","/financeiro/obterfichafinanceira/numeroproposta");
        //String url = requestBase + boleto;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String strObject = mapper.writeValueAsString(detalhesBoletoResponse);

            HttpEntity<String> entity = new HttpEntity<>(strObject, headers);
            ResponseEntity<FichaFinanceiraResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, FichaFinanceiraResponse.class);
            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("FichaFinanceira ->>> " + HttpStatus.OK);
                financeiraResponse = retorno.getBody();
                return financeiraResponse;
            } else {
                log.warn("FichaFinanceira ->>> " + financeiraResponse);
                return financeiraResponse;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.warn("FichaFinanceira ->>> " + financeiraResponse);
            return financeiraResponse;
        }
    }

    public byte[] gerarBoleto(FichaFinancieraBoleto financieraBoleto) {

        log.info("GERAR BOLETO ->>> gerarBoleto");

        String url = "http://172.18.203.21:8090/est-corretorboletoebs-api-rs-1.0/financeiro/gerarboleto";

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

            HttpEntity<FichaFinancieraBoleto> entity = new HttpEntity<>(financieraBoleto, headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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