package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.DadosContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretora;
import br.com.odontoprev.portalcorretor.service.dto.ContratoCorretoraPreenchido;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.odontoprev.portalcorretor.util.Constantes;

@Service
public class ContratoCorretoraService {

	private static final Log log = LogFactory.getLog(ContratoCorretoraService.class);

	@Value("${odontoprev.servicebase.url}")
	private String requesBasetUrl;

	@Value("${odontoprev.contratocorretora.susep}")
	private String queryCdSusep;

	@Value("${odontoprev.contratocorretora.metodo}")
	private String metodoContratoCorretora;


	@Autowired
	private ApiManagerTokenService apiManagerTokenService;

	public ContratoCorretoraPreenchido obterModeloContrato(DadosContratoCorretora dadosContratoCorretora) {

		String cdSusep = dadosContratoCorretora.getCodSusep();

		String url = requesBasetUrl + metodoContratoCorretora + "/";
		url += dadosContratoCorretora.getCdCorretora() + Constantes.CONTRATO_TIPO;
		url += cdSusep != "" ? Constantes.CONTRATO_CORRETAGEM_V1 : Constantes.CONTRATO_INTERMEDIACAO_V1;
		url += cdSusep != "" ? queryCdSusep + cdSusep : "";

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

	public ContratoCorretora postContratoCorretora(DadosContratoCorretora dadosContratoCorretora) {

		log.info("salvar contrato corretora - ini");
		String url = requesBasetUrl + metodoContratoCorretora;

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
			headers.set("Content-Type", "application/json");

			ContratoCorretora contratoCorretora = new ContratoCorretora();
			contratoCorretora.setCdCorretora(dadosContratoCorretora.getCdCorretora());
			contratoCorretora.setCdSusep(dadosContratoCorretora.getCodSusep());
			Long cdContratoModelo = dadosContratoCorretora.getCodSusep() != "" ? 1L : 2L;
			contratoCorretora.setCdContratoModelo(cdContratoModelo);

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
			String stringJson = mapper.writeValueAsString(contratoCorretora);

			HttpEntity<String> entity = new HttpEntity<String>(stringJson, headers);

			ResponseEntity<ContratoCorretora> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, ContratoCorretora.class);

			if (retorno.getStatusCode() == HttpStatus.OK) {
				return retorno.getBody();
			} else {
				log.info("salvarContrato - erro");
				log.error("Erro ao salvar contrato: Status{" + retorno.getStatusCode() + "}");
				return null;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			log.info("salvarContrato - erro");
			log.error(e);
			return null;
		}
	}

}
