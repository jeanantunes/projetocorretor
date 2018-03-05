package br.com.odontoprev.portalcorretor.service;

import java.util.HashMap;
import java.util.Map;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.LoginResponse;


@Service
public class LoginService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.service.login}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public UsuarioSession Autenticar(String usuario, String senha) {

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
                return new UsuarioSession().setDados(loginRetorno.getBody());
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}


