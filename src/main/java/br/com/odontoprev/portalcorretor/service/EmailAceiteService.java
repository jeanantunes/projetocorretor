package br.com.odontoprev.portalcorretor.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.odontoprev.portalcorretor.service.dto.EmailAceite;
import br.com.odontoprev.portalcorretor.service.dto.EmailAceiteResponse;
import br.com.odontoprev.portalcorretor.service.dto.TokenAceite;

@Service
public class EmailAceiteService {
	
	private static final Log log = LogFactory.getLog(EmailAceiteService.class);
	 
	 @Value("${odontoprec.service.base}")
	 private String requesBasetUrl;
	 
	 @Value("${odontoprev.tokenAceite.confirmacao}")
	 private String metodoTokenAceiteConfirmacao_PUT;
	 
	 @Value("${odontoprev.tokenAceite.token}")
	 private String dadosTokenAceite;
	 
	 @Autowired
	 private ApiManagerTokenService apiManagerTokenService;
	 
	 public TokenAceite ObterDadosTokenAceite(String token) {
		 
		 String url = requesBasetUrl + dadosTokenAceite + token;
	     RestTemplate restTemplate = new RestTemplate();
		 
	     try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<TokenAceite> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, TokenAceite.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new TokenAceite();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new TokenAceite();
        }		
	 }
	 
	 public EmailAceiteResponse confirmarEmailAceite(EmailAceite emailAceite) {
		 
		 	log.info("Confirmar Token Aceite - Email ");
		 
	        String url = requesBasetUrl + "/" + metodoTokenAceiteConfirmacao_PUT;
	        RestTemplate restTemplate = new RestTemplate();
	        //EmailAceiteResponse result = null;
	        
	        try {
	            HttpHeaders headers = new HttpHeaders();
	            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            
	            ObjectMapper mapper = new ObjectMapper();
	            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
	            String object = mapper.writeValueAsString(emailAceite);

	            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

	            ResponseEntity<EmailAceiteResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, EmailAceiteResponse.class);

	            
	            ///TODO: verificar validações
	            
	            if (retorno.getStatusCode() == HttpStatus.OK) {
	                return retorno.getBody();
	            } else {
	                return new EmailAceiteResponse(400, "Falha ao confirmar token de aceite");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new EmailAceiteResponse(400, "Falha ao confirmar token de aceite");
	        }
	    }
}
