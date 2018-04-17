package br.com.odontoprev.portalcorretor.service;

import java.time.LocalDate;
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
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.service.entity.FiltroStatusProposta;

@Service
public class DashService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.service.dash}")
    private String metodoDash;

    @Value("${odontoprev.service.dash_propostaPF}")
    private String metodoPropostaPFList;

    @Value("${odontoprev.service.dash_propostaPME}")
    private String metodoPropostaPMEList;

    @Value("${odontoprev.service.dash_propostaCriticadas}")
    private String metodoPropostasCriticadas;
    
    private static Log log = LogFactory.getLog(DashService.class);


    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    // Obtem dash por numero de documento
    public DashResponse[] obterPorDocumento(LocalDate dataInicio, LocalDate dataFim, String cnpjCPF) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();
        String url = requesBasetUrl + "/" + metodoDash;

        if(dataInicio!=null) {
        	resquestMap.put("dtInicio", dataInicio.toString());
        } 
        if(dataFim!=null) {
        	resquestMap.put("dtFim", dataFim.toString());
        }
        if(cnpjCPF!=null && cnpjCPF.length() > 11) {
        	resquestMap.put("cnpj", cnpjCPF);
        } else {
        	resquestMap.put("cpf", cnpjCPF);
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<>(resquestMap, headers);
            ResponseEntity< ? extends DashResponse[]> retorno = restTemplate.postForEntity(url, entity, new DashResponse[]{}.getClass());

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new DashResponse[]{};
            }

        } catch (Exception e) {
        	log.error(e);
            return new DashResponse[]{};
        }

    }

    public DashboardPropostas ObterListaPropostaPME(FiltroStatusProposta statusProposta, String documento) {
        return getDashboardPropostas(statusProposta, documento, metodoPropostaPMEList);
    }

    public DashboardPropostas ObterListaPropostaPF(FiltroStatusProposta statusProposta, String documento) {
        return getDashboardPropostas(statusProposta, documento, metodoPropostaPFList);
    }

    private DashboardPropostas getDashboardPropostas(FiltroStatusProposta statusProposta, String documento, String metodoProposta) {
        String url = requesBasetUrl + metodoProposta + "/" + statusProposta.getValue() + "/" + documento;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                if (retorno.getBody().getDashboardPropostasPF() != null && retorno.getBody().getDashboardPropostasPME() != null) {
                    return retorno.getBody();
                }
            }
            return new DashboardPropostas();
        } catch (Exception e)
        {
            e.printStackTrace();
            return new DashboardPropostas();
        }
    }

//    public void ObterPropostasCriticadas(String documento) {
//
//        String url = requesBasetUrl + metodoPropostasCriticadas + documento;
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);
//
//            if (retorno.getStatusCode() == HttpStatus.OK) {
//                return retorno.getBody();
//            } else {
//                //
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //return new DashboardPropostas();
//        }
//
//
//    }
}