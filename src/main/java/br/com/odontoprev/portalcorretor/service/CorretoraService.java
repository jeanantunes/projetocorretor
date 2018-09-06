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

import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.CorretoraResponse;

@Service
public class CorretoraService {

	private static final Log log = LogFactory.getLog(CorretoraService.class);

	@Value("${odontoprev.servicebase.url}")
	private String requesBasetUrl;

	@Value("${odontoprev.corretoras.DadosCorretora}")
	private String dadosCorretora;

	@Value("${odontoprev.corretoras.salvarEmailCorretora}")
	private String salvarEmailCorretora;

	@Autowired
	private ApiManagerTokenService apiManagerTokenService;

	public Corretora obterDadosCorretora(String cnpj) {

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
			// e.printStackTrace();
			log.error(e);
			return new Corretora();
		}
	}

	public CorretoraResponse salvarEmailCorretora(Corretora corretora) {

	 	log.info("salvarEmailCorretora - ini");
	 	log.info(corretora);
	 	
		String url = requesBasetUrl + salvarEmailCorretora;
		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);
			
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String stringJson = mapper.writeValueAsString(corretora);

			HttpEntity<String> entity = new HttpEntity<String>(stringJson, headers);

			ResponseEntity<CorretoraResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entity, CorretoraResponse.class);

			if (retorno.getStatusCode() == HttpStatus.OK) {
			 	log.info("salvarEmailCorretora - fim ok");
				return retorno.getBody();
			} else {
			 	log.info("salvarEmailCorretora - fim nao-ok");
			 	log.info("retorno.getStatusCode():[" + retorno.getStatusCode() + "]");
				return null;
			}

		} catch (Exception e) {
			// e.printStackTrace();
		 	log.info("salvarEmailCorretora - erro");
			log.error(e);
			return null;
		}
	}

}
