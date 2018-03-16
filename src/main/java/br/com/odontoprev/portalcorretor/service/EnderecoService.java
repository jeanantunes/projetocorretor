package br.com.odontoprev.portalcorretor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.EnderecoResponse;

@Service
public class EnderecoService {

	 @Value("${odontoprev.service.busca.por.cep}")
	 private String requesBasetUrl;
	 
	 @Autowired
	 private ApiManagerTokenService apiManagerTokenService;
	 
	 public EnderecoResponse ObterEnderecoCorretora(String cep) {
		 apiManagerTokenService = new ApiManagerTokenService();		 
	     RestTemplate restTemplate = new RestTemplate();

	     try {
	            HttpHeaders headers = new HttpHeaders();
	            headers.set("Authorization", "Bearer"+ apiManagerTokenService.getToken());
	            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	            ResponseEntity<EnderecoResponse> retorno = restTemplate.exchange(requesBasetUrl, HttpMethod.GET, entity, EnderecoResponse.class);


	            if (retorno.getStatusCode() == HttpStatus.OK) {
	                return new EnderecoResponse();
	            } else {
	                return new EnderecoResponse();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new EnderecoResponse();
	        }				 
	 }
}
