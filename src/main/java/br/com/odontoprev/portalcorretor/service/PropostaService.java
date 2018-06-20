package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.DetalhesPropostaResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PropostaService {

    private static final Log log = LogFactory.getLog(PropostaService.class);

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.detalhes.proposta}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public DetalhesPropostaResponse detalhesProposta(String cdVenda) {
        DetalhesPropostaResponse detalhesPropostaResponse = null;

        String url = requestBase + metodo + cdVenda;
        //String url = "http://localhost:9090/proposta/dados/critica/venda/" + cdVenda;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<DetalhesPropostaResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DetalhesPropostaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("DetalhesPropostaService ->>> " + retorno.getStatusCode());
                detalhesPropostaResponse = retorno.getBody();
                return detalhesPropostaResponse;
            } else {
                log.error("DetalhesPropostaService ->>> " + retorno.getStatusCode() + "\n" + retorno.getBody());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("DetalhesPropostaService ->>> " + e);
            return null;
        }
    }
}