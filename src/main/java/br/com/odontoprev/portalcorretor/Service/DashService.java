package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;
import br.com.odontoprev.portalcorretor.Service.dto.PropostaResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    //@Value("${odontoprev.service.dash}")
    private String metodoDash;

    //@Value("${odontoprev.service.propostaPF}")
    private String metodoPropostaPFList;

    //@Value("${odontoprev.service.propostaPJ}")
    private String metodoPropostaPJList;


    public DashResponse ObterPorDocumento(Date dataInicio,
                                          Date dataFim,
                                          String nomeVendedor,
                                          String tipoPlano,
                                          String cnpjCPF) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();
        String url = requesBasetUrl + metodoDash;

        resquestMap.put("", cnpjCPF);
        resquestMap.put("", dataInicio.toString());
        resquestMap.put("", dataFim.toString());
        resquestMap.put("", nomeVendedor);
        resquestMap.put("", tipoPlano);

        try {
            ResponseEntity<DashResponse> retorno = restTemplate.postForEntity(url, resquestMap, DashResponse.class);
            //return new LoginResponse(login.getUsuario(), "Corretor", Long.parseLong(loginRetorno.getBody().getCodigo()));

            return null;

        } catch (Exception e) {
            return null;
        }
    }

    public List<PropostaResponse> ObterListaPropostaPME(String documento)
    {
        return ObterPropostaList(metodoPropostaPJList, documento);
    }


    private List<PropostaResponse> ObterPropostaList(String metodo,String documento) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();
        String url = requesBasetUrl + metodo;

        try {
            ResponseEntity<PropostaResponse[]> retorno = restTemplate.postForEntity(url, resquestMap, PropostaResponse[].class );
            //return new LoginResponse(login.getUsuario(), "Corretor", Long.parseLong(loginRetorno.getBody().getCodigo()));

            return null;

        } catch (Exception e) {
            return null;
        }

    }


}
