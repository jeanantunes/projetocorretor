package br.com.odontoprev.portalcorretor.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.Corretora;

@Service
public class CorretoraService {
	
	 private static final Log log = LogFactory.getLog(CorretoraService.class);

	 @Value("${odontoprev.servicebase.url}")
	 private String requesBasetUrl;
	 
	 @Value("${odontoprev.corretoras.DadosCorretora}")
	 private String dadosCorretora;
	 
	 @Autowired
	 private ApiManagerTokenService apiManagerTokenService;
	 public Corretora ObterDadosCorretora(String cnpj) {
		 
		 String url = requesBasetUrl + dadosCorretora + cnpj;
	     RestTemplate restTemplate = new RestTemplate();
		 
	     try {
	            HttpHeaders headers = new HttpHeaders();
	            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
	            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	            ResponseEntity<Corretora> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Corretora.class);

	            if (retorno.getStatusCode() == HttpStatus.OK) {
	                return retorno.getBody();
	            } else {
	                return new Corretora();
	            }

	        } catch (Exception e) {
	            //e.printStackTrace();
	            log.error(e); 
	            return new Corretora();
	        }				 
	 }
	 
}
