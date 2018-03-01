package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";
    //@Value("${odontoprev.service.login}")
    private String metodo = "login";

    public LoginResponse Autenticar(String usuario, String senha) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("login", usuario);
        loginMap.put("senha", senha);

        try {
            ResponseEntity<LoginResponse> loginRetorno = restTemplate.postForEntity((requesBasetUrl + metodo), loginMap, LoginResponse.class);
            if (loginRetorno.getStatusCode() == HttpStatus.OK) {
                return loginRetorno.getBody();
            }

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}


