//package br.com.odontoprev.portalcorretor.service;
//
//import br.com.odontoprev.portalcorretor.service.dto.CidadeResponse;
//import br.com.odontoprev.portalcorretor.service.dto.DashboardPropostas;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class RedeCredenciadaService {
//    @Autowired
//    private ApiManagerTokenService apiManagerTokenService;
//
//    @Value("${odontoprev.servicebase.url}")
//    private String requesBasetUrl;
//
//    @Value("${redecredenciada.Estado}")
//    private String metodoEstado;
//
//    @Value("${redecredenciada.Cidade}")
//    private String metodoCidade;
//
//    @Value("${odontoprev.redecredenciada.metodo}")
//    private String redecredenciada_metodoDash;
//
//    public List<String> ObterEstado() {
//        String url = requesBasetUrl + metodoEstado;
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<String[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, String[].class);
//
//            if (retorno.getStatusCode() == HttpStatus.OK) {
//                return Arrays.asList(retorno.getBody());
//            } else {
//                return Arrays.asList(new String[0]);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Arrays.asList(new String[0]);
//        }
//    }
//
//    public List<CidadeResponse> ObterCidade() {
//        String url = requesBasetUrl + metodoCidade;
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<CidadeResponse[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, CidadeResponse[].class);
//
//            if (retorno.getStatusCode() == HttpStatus.OK) {
//                return Arrays.asList(retorno.getBody());
//            } else {
//                return Arrays.asList(new CidadeResponse[0]);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Arrays.asList(new CidadeResponse[0]);
//        }
//    }
//
//    //public
//
//
//    public void ObterRedeCredenciada() {
//
//        String url = requesBasetUrl + redecredenciada_metodoDash;
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//            ResponseEntity<DashboardPropostas> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DashboardPropostas.class);
//
//            if (retorno.getStatusCode() == HttpStatus.OK) {
//                //return retorno.getBody();
//            } else {
//                //
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //return new DashboardPropostas();
//        }
//    }
//}
