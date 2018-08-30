package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.*;
import br.com.odontoprev.portalcorretor.service.dto.BeneficiariosPropostaResponsePagination;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaPropostaResponse;
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
import java.util.List;

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

    public PropostaCritica detalhesProposta(String cdVenda) {
        PropostaCritica propostaCritica = null;

        String url = requestBase + metodo + cdVenda;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<PropostaCritica> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, PropostaCritica.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("DetalhesPropostaService ->>> " + retorno.getStatusCode());
                propostaCritica = retorno.getBody();
                return propostaCritica;
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

    public EmpresaPropostaResponse detalhesPropostaPME(String cdEmpresa) {
        EmpresaPropostaResponse empresaPropostaResponse = null;

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/empresa/" + cdEmpresa);
        //String url = "localhost:8090/empresa/" + cdEmpresa;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<EmpresaPropostaResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, EmpresaPropostaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("EmpresaPropostaService ->>> " + retorno.getStatusCode());
                empresaPropostaResponse = retorno.getBody();
                return empresaPropostaResponse;
            } else {
                log.error("EmpresaPropostaService ->>> " + retorno.getStatusCode() + "\n" + retorno.getBody());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("EmpresaPropostaService ->>> " + e);
            return null;
        }
    }

    public List<BeneficiariosPropostaResponsePagination> detalhesBeneficiarioPropostaPME(Long cdEmpresa, Long numpag, Long tampag) {

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/beneficiarios/empresa/" + cdEmpresa + "?numpag=" + numpag + "&tampag=" + tampag);
        //String url = "http://localhost:8090/beneficiarios/empresa/" + cdEmpresa + "?numpag=" + numpag + "&tampag=" + tampag;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<BeneficiariosPropostaResponsePagination> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, BeneficiariosPropostaResponsePagination.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("BeneficiarioPropostaService ->>> " + retorno.getStatusCode());
                return Arrays.asList(retorno.getBody());
            } else {
                log.error("BeneficiarioPropostaService ->>> " + retorno.getStatusCode() + "\n" + retorno.getBody());
                return Arrays.asList(new BeneficiariosPropostaResponsePagination[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("BeneficiarioPropostaService ->>> " + e);
            return Arrays.asList(new BeneficiariosPropostaResponsePagination[0]);
        }
    }

    public FichaFinanceiraResponse listarBoleto(DetalhesBoletoResponse detalhesBoletoResponse) {
        FichaFinanceiraResponse financeiraResponse = null;

        log.info("LISTAR BOLETO ->>> detalhesBoleto");

        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/obterfichafinanceira/codigoassociado
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/obterfichafinanceira/numeroproposta
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/gerarboleto
        //https://api-it3.odontoprev.com.br:8243/corretor/boleto/1.0/financeiro/gerarboletofile

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretor/boleto/1.0/financeiro/obterfichafinanceira/numeroproposta");

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

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretor/boleto/1.0/financeiro/gerarboleto");

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

    public ResponseEntity<String> gerarArquivoContratacao(Long cdEmpresa) {
        log.info("GERAR ARQUIVO CONTRATACAO ->>> gerarArquivoContratacao");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/arquivocontratacao/empresa/" + cdEmpresa + "/arquivo");
        //String url = "http://localhost:8090/arquivocontratacao/empresa/" + cdEmpresa + "/arquivo";

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

            HttpEntity<Long> entity = new HttpEntity<>(cdEmpresa, headers);
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

    public ArquivoContratacao gerarArquivoContratacaoJson(Long cdEmpresa) {
        log.info("GERAR ARQUIVO CONTRATACAO ->>> gerarArquivoContratacao");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "/corretorservicos/1.0/arquivocontratacao/empresa/" + cdEmpresa + "/json");
        //String url = "http://localhost:8090/arquivocontratacao/empresa/" + cdEmpresa + "/json";

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

            HttpEntity<Long> entity = new HttpEntity<>(cdEmpresa, headers);
            ResponseEntity<ArquivoContratacao> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ArquivoContratacao.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}