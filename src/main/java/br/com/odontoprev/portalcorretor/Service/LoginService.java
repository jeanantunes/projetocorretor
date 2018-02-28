package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "https://api-it1.odontoprev.com.br:8243/";
    //@Value("${odontoprev.service.login}")
    private String metodo = "dcss/login/1.0";

    public LoginResponse Autenticar(String usuario, String senha) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("login", usuario);
        loginMap.put("senha", senha);

        try {
            ResponseEntity<LoginResponse> loginRetorno = restTemplate.postForEntity((requesBasetUrl + metodo), loginMap, LoginResponse.class);
            //return new LoginResponse(login.getUsuario(), "Corretor", Long.parseLong(loginRetorno.getBody().getCodigo()));

            System.out.print(loginRetorno);

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}


