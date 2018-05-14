package br.com.odontoprev.portalcorretor.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.Perfil;

@Service
public class PerfilService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ApiManagerTokenService apiManagerTokenService;

	@Value("${odontoprev.servicebase.url}")
	private String baseURL;

	@Value("${odontoprev.corretor.metodo}")
	private String metodo;

	private Logger logger;

	public Perfil buscarPerfilPorUsuario(String usuario) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			String apiURL = baseURL + metodo + usuario;
			ResponseEntity<Perfil> retorno = restTemplate.exchange(apiURL, HttpMethod.GET, entity, Perfil.class);

			if (retorno.getStatusCode() != HttpStatus.OK) {
				return null;
			}
			return retorno.getBody();

		} catch (Exception ex) {
			logger.error("ERROR  ");
			return null;
		}
	}

}
