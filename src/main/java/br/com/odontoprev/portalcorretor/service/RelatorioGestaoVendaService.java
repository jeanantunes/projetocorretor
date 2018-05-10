package br.com.odontoprev.portalcorretor.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.odontoprev.portalcorretor.service.dto.RelatorioGestaoVenda;

@Service
public class RelatorioGestaoVendaService {

	private static final Log log = LogFactory.getLog(RelatorioGestaoVendaService.class);
	
	@Value("${odontoprec.service.base}")
	private String requesBasetUrl;
	
	//TODO: erificar properties
	@Value("${odontoprev.gestaoVenda.csv}")
	private String gestaoVendaCSV;
	
	@Autowired
	private ApiManagerTokenService apiManagerTokenService;
	
	public RelatorioGestaoVenda gerarCSV(String cnpj) {
		
		 log.info("Relatorio Gestao de Venda - gerarCSV "); 

		 String url = requesBasetUrl + gestaoVendaCSV + cnpj;
		 
		 RestTemplate restTemplate = new RestTemplate();

	     try {
           HttpHeaders headers = new HttpHeaders();
           headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
           HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
           ResponseEntity<RelatorioGestaoVenda> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, RelatorioGestaoVenda.class);

           if (retorno.getStatusCode() == HttpStatus.OK) {
               return retorno.getBody();
           } else {
               return new RelatorioGestaoVenda();
           }

       } catch (Exception e) {
           e.printStackTrace();
           return new RelatorioGestaoVenda();
       }		
	}
}
