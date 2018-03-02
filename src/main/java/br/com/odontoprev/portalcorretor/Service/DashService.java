package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.Service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

    //@Value("${odontoprev.service.dash}")
    private String metodoDash = "propostasDashBoard/";

    //@Value("${odontoprev.service.propostaPF}")
    private String metodoPropostaPFList = "dashboardPropostaPF/";

    //@Value("${odontoprev.service.propostaPME}")
    private String metodoPropostaPMEList = "dashboardPropostaPME/";

    //@Autowired
    //private ApiManagerTokenService apiManagerTokenService;

    //TODO: valores fixos para teste

    public DashResponse ObterPorDocumento(LocalDate dataInicio,
                                          LocalDate dataFim,
                                          String cnpjCPF) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();
        String url = requesBasetUrl + metodoDash;

        resquestMap.put("dtInicio", dataInicio.toString());
        resquestMap.put("dtFim", dataFim.toString());
        //resquestMap.put("cpf", cnpjCPF);

        resquestMap.put("cpf", "38330982874");

        try {
            ResponseEntity<DashResponse> retorno = restTemplate.postForEntity(url, resquestMap, DashResponse.class);
            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            }
            return new DashResponse();

        } catch (Exception e) {
            return null;
        }
    }

    public DashboardPropostas ObterListaPropostaPME(FiltroStatusProposta statusProposta, String documento) {
        return getDashboardPropostas(statusProposta, documento, metodoPropostaPMEList);
        //return getDashboardPropostas(statusProposta, documento, metodoPropostaPFList);
    }

    public DashboardPropostas ObterListaPropostaPF(FiltroStatusProposta statusProposta, String documento) {
        return getDashboardPropostas(statusProposta, documento, metodoPropostaPFList);
        //return getDashboardPropostas(statusProposta, documento, metodoPropostaPFList);
    }

    private DashboardPropostas getDashboardPropostas(FiltroStatusProposta statusProposta, String documento, String metodoPropostaPFList) {
        String url = requesBasetUrl + metodoPropostaPFList + "/" + statusProposta.getValue() + "/" + documento;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new DashboardPropostas();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DashboardPropostas();
        }
    }
}