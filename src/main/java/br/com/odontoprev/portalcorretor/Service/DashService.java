package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DashService {
    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "";
    //@Value("${odontoprev.service.login}")
    private String metodo = "";

    public DashResponse ObterPorDocumento(Date dataInicio,
                                          Date dataFim ,
                                          String nomeVendedor,
                                          String tipoPlano ,
                                          String cnpjCPF) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();

        resquestMap.put("", cnpjCPF);
        resquestMap.put("", dataInicio.toString());
        resquestMap.put("", dataFim.toString());
        resquestMap.put("", nomeVendedor);
        resquestMap.put("", tipoPlano);


        try {
            ResponseEntity<DashResponse> retorno = restTemplate.postForEntity((requesBasetUrl + metodo), resquestMap, DashResponse.class);
            //return new LoginResponse(login.getUsuario(), "Corretor", Long.parseLong(loginRetorno.getBody().getCodigo()));

            return null;

        } catch (Exception e) {
            return null;
        }
    }
}
