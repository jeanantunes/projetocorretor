package br.com.odontoprev.portalcorretor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.dto.LoginADResponse;
import br.com.odontoprev.portalcorretor.service.dto.LoginResponse;

@Service
public class LoginService {

	private static final Log log = LogFactory.getLog(LoginService.class);

	@Value("${odontoprev.servicebase.url}")
	private String requesBasetUrl;

	@Value("${odontoprev.service.login}")
	private String metodo;

	@Value("${odontoprev.corretor.api.contexto.api.url}")
	private String corretorApiURL;

	@Value("${odontoprev.service.url.metodo.autenticacao.ad}")
	private String metodoAutenticacaoAd;

	@Autowired
	private ApiManagerTokenService apiManagerTokenService;

	public UsuarioSession Autenticar(String usuario, String senha) {
		final long start = new Date().getTime();
		log.info("LoginService.Autenticar - inicio");
		UsuarioSession usuarioSession = null;

		usuarioSession = autenticarPortalCorretor(usuario, senha);
		if (usuarioSession == null) {
			usuarioSession = autenticarAD(usuario, senha);
		}

		long time = new Date().getTime() - start;
		log.info("LoginService.Autenticar - fim - " + time + "ms");

		return usuarioSession;
	}

	private UsuarioSession autenticarPortalCorretor(String usuario, String senha) {
		try {

			String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", corretorApiURL + metodo);
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> loginMap = new HashMap<>();
			loginMap.put("usuario", usuario);
			loginMap.put("senha", senha);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
			HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

			ResponseEntity<LoginResponse> loginRetorno = restTemplate.exchange(url, HttpMethod.POST, entityReq,
					LoginResponse.class);
			if (loginRetorno.getStatusCode() == HttpStatus.OK) {
				return new UsuarioSession().setDados(loginRetorno.getBody());
			}
			return null;
		} catch (Exception e) {
			log.error("ERRO AO AUTENTICAR", e);
			return null;
		}
	}

	private UsuarioSession autenticarAD(String usuario, String senha) {
		try {
					
				String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", metodoAutenticacaoAd);
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
				map.add("username", usuario);
				map.add("password", senha);
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
				ResponseEntity<String> loginRetorno = restTemplate.postForEntity(url, request, String.class);
				
				
				if (loginRetorno.getStatusCode() == HttpStatus.OK) {
					LoginADResponse LoginADResponse = new Gson().fromJson(loginRetorno.getBody(), LoginADResponse.class);
						if("success".equals(LoginADResponse.getStatuscode())) {
							UsuarioSession usuarioSession = new UsuarioSession();
							usuarioSession.setNomeUsuario(LoginADResponse.getNome());
							usuarioSession.setPerfil("Adiministrador");
							return usuarioSession;
						}
				}
				
				return null;
		
		 	} catch(RestClientException e) {
		 		log.error("ERRO AO AUTENTICAR", e);
				return null;
		 	}
			  catch(Exception e) {
		 		log.error("ERRO AO AUTENTICAR", e);
				return null;
		 	}
	}
}
