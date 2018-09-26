package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.service.dto.*;

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

import java.util.*;

@Service
public class ForcaVendaService {

    private static final Log log = LogFactory.getLog(ForcaVendaService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.forcavenda.metodo}")
    private String metodoGetPorDocuemnto_Post_Put;

    private String ativar = "forcavenda/status-ativo";
    
    private String excluir = "forcavenda/status-excluido";
    
    private String reprovar = "forcavenda/status-reprovado";

    @Value("${odontoprev.forcavenda.ListaPorCorretora}")
    private String metodoListaPorCorretora;

    @Value("{odontoprev.corretor.api.contexto.api.url}")
    private String contextoApiManager;

    @Value("{odontoprev.web.forcavenda.bloqueado}")
    private String metodoForcaVendaBloqueio;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    @SuppressWarnings("null")
	public long Criar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + "/" + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String object = mapper.writeValueAsString(forcaVenda);

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                result = retorno.getBody();
                log.info("Forca cadastrado " + result.getId());
                return result.getId();
            } else {
                log.info("Erro ao cadastrar Forca " + result.getMensagem());
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("Deu ruim ao cadastrar Forca ");
            return 0;
        }


    }

    public AtivarResponse ativar(String cpf) {
        String url = requesBasetUrl + "/" + ativar;
        RestTemplate restTemplate = new RestTemplate();
        //ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> loginMap = new HashMap<>();
            loginMap.put("cpf", cpf);
            HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

            ResponseEntity<AtivarResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, AtivarResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new AtivarResponse("0", "Falha ao ativar cpf");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AtivarResponse("0", "Falha ao ativar cpf");
        }

    }
    
    public ExcluirResponse excluir(String cpf) {
        String url = requesBasetUrl + "/" + excluir;
        RestTemplate restTemplate = new RestTemplate();
       
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> loginMap = new HashMap<>();
            loginMap.put("cpf", cpf);
            HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

            ResponseEntity<ExcluirResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, ExcluirResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new ExcluirResponse("0", "Falha ao excluir cpf");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ExcluirResponse("0", "Falha ao excluir cpf");
        }
    }

    public ReprovarResponse reprovar(String cpf) {
        String url = requesBasetUrl + "/" + reprovar;
        RestTemplate restTemplate = new RestTemplate();
       
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> loginMap = new HashMap<>();
            loginMap.put("cpf", cpf);
            HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

            ResponseEntity<ReprovarResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, ReprovarResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new ReprovarResponse("0", "Falha ao reprovar cpf");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ReprovarResponse("0", "Falha ao reprovar cpf");
        }
    }
    
    public boolean Alterar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        //ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String object = mapper.writeValueAsString(forcaVenda);

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<ForcaVenda> ObterListaPorCorretora(Integer codigoCorretora) {
        String url = requesBasetUrl + metodoListaPorCorretora + codigoCorretora;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<ForcaVenda[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda[].class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(retorno.getBody());
            } else {
                return Arrays.asList(new ForcaVenda[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.asList(new ForcaVenda[0]);
        }
    }

    public ForcaVenda ObterPorDocumento(String documento) {
        String url = requesBasetUrl + "/"+ metodoGetPorDocuemnto_Post_Put + documento;
        RestTemplate restTemplate = new RestTemplate();
        //ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ForcaVenda> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new ForcaVenda();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ForcaVenda();
        }
    }

    public ResponseEntity<Login> verificaBloqueio(long cdForcaVenda) throws ApiTokenException {

        log.info("VERIFICA FORCA VENDA BLOQUEIO ->>> verificaBloqueio()");

        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", "" + contextoApiManager + metodoForcaVendaBloqueio + "?cdForcaVenda=" + cdForcaVenda);
        //String url = "http://localhost:8090/forcavenda/bloqueio?cdForcaVenda=" + cdForcaVenda;

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json")));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());

        HttpEntity<Login> entity = new HttpEntity<>(headers);
        ResponseEntity<Login> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Login.class);

        return retorno;
    }
}
