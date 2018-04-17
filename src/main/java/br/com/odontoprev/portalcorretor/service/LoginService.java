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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.dto.LoginResponse;


@Service
public class LoginService {
	
	private static final Log log = LogFactory.getLog(LoginService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.service.login}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public UsuarioSession Autenticar(String usuario, String senha) {
    	final long start = new Date().getTime();
    	log.info("LoginService.Autenticar - inicio");
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("usuario", usuario);
        loginMap.put("senha", senha);

        String url = requesBasetUrl + metodo;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

            ResponseEntity<LoginResponse> loginRetorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, LoginResponse.class);
            if (loginRetorno.getStatusCode() == HttpStatus.OK) {
            	long time = new Date().getTime() - start;
            	log.info("LoginService.Autenticar - fim - "+time+"ms");
                return new UsuarioSession().setDados(loginRetorno.getBody());
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}


