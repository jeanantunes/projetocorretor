package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.DadosContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretoraPreenchido;
import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.CorretoraResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContratoCorretoraService {

	private static final Log log = LogFactory.getLog(ContratoCorretoraService.class);

	@Value("${odontoprev.servicebase.url}")
	private String requesBasetUrl;

	@Value("${odontoprev.corretoras.DadosCorretora}")
	private String dadosCorretora;

	@Value("${odontoprev.corretoras.salvarEmailCorretora}")
	private String salvarEmailCorretora;

	@Autowired
	private ApiManagerTokenService apiManagerTokenService;

	public ContratoCorretoraPreenchido obterModeloContrato(DadosContratoCorretora dadosContratoCorretora) {


		String cdSusep = dadosContratoCorretora.getCodSusep();
		///CONTRATOCORRETORA/{CDCORRETORA}/TIPO/{CDTIPO}?susep={cdSusep}

		String url = "http://localhost:8090/contratocorretora/";


		url += dadosContratoCorretora.getCdCorretora() + "/tipo";
		url += cdSusep != "" ? "/1" : "/2";
		url += cdSusep != "" ? "?cdSusep=" + cdSusep : "";


		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<ContratoCorretoraPreenchido> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ContratoCorretoraPreenchido.class);

			if (retorno.getStatusCode() == HttpStatus.OK) {
				return retorno.getBody();
			} else {
				return null;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			log.error(e);
			return null;
		}
	}

}
