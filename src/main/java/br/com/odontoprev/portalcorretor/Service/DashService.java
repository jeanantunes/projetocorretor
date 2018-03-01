package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.Service.dto.PropostaResponse;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroProposta;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DashService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

    //@Value("${odontoprev.service.dash}")
    private String metodoDash;

    //@Value("${odontoprev.service.propostaPF}")
    private String metodoPropostaPFList;

    //@Value("${odontoprev.service.propostaPJ}")
    private String metodoPropostaPMEList = "dashboardPropostaPME/";


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


    public List<PropostaResponse> ObterPropostaList(FiltroProposta listaProposta, String metodo, String documento) {

        RestTemplate restTemplate = new RestTemplate();
        String url = requesBasetUrl + metodo + listaProposta.getValue() + "/" + documento;

        try {
            ResponseEntity<List<PropostaResponse>> retorno = restTemplate.postForEntity(url, HttpMethod.GET, null, PropostaResponse[].class);

            return retorno.getBody();

        } catch (Exception e) {
            return null;
        }

//        {
//            "dashboardPropostasPME": [
//            {
//                "cdEmpresa": 323,
//                    "nome": "SETAI NOME FANTASIA",
//                    "statusVenda": "Aprovado",
//                    "dataVenda": "26/02/2018",
//                    "cnpj": "12311200003"
//            },
//            {
//                "cdEmpresa": 281,
//                    "nome": "SETAI NOME FANTASIA",
//                    "statusVenda": "Aprovado",
//                    "dataVenda": "25/02/2018",
//                    "cnpj": "12311200003"
//            },
//            {
//                "cdEmpresa": 223,
//                    "nome": "SETAI NOME FANTASIA",
//                    "statusVenda": "Aprovado",
//                    "dataVenda": "25/02/2018",
//                    "cnpj": "12311200003"
//            }]
 //   }

    }
    
}
