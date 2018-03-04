package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.Equipe;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ForcaVendaService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

//    //@Value("${odontoprev.service.forcaVendasPendente}")
//    private String metodoForcaVendasPendente = "dashboard/forcavenda/aguardando-aprovacao/";
//
//    private String metodoSuaEquipe_1 = "forcavendastatus/";
//
//    private String metodoSuaEquipe_2 = "/corretora/";


    ///forcavenda/{cdForcaVenda}/corretora/{cnpj}
//    public List<ForcaVenda> ObterPelaCorretora(int status, int codigoEmpresa) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> resquestMap = new HashMap<>();
//        String url = requesBasetUrl + metodoSuaEquipe_1 + status + metodoSuaEquipe_2 + codigoEmpresa;
//        List<Equipe> result = new ArrayList<>();
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            //headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<Equipe[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Equipe[].class);
//
//            if (retorno.getStatusCode() == HttpStatus.OK) {
//                return Arrays.asList(retorno.getBody());
//            } else {
//                return result;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return result;
//        }
//    }
//
//    public long ObterForcaVendaPendente(long codigoEmpresa) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> resquestMap = new HashMap<>();
//        String url = requesBasetUrl + metodoForcaVendasPendente + codigoEmpresa;
//
////        try {
////            ResponseEntity<long> retorno = restTemplate.getForEntity(url, long.class);
////            if (retorno.getStatusCode() == HttpStatus.OK) {
////                return retorno.getBody();
////            }
////            return 0;
////
////        } catch (Exception e) {
////            return 0;
////        }
//        return 0;
//    }


}
