package br.com.odontoprev.portalcorretor.service;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.exceptions.SerasaConsultaException;
import br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice.DataLicensingService;
import br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice.DataLicensingServicePortType;
import br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice.WSSecurityHeaderSOAPHandler;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.Pacote;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.ParametersInPJ;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.PessoaJuridica;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.RetornoPJ;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.TipoConsulta;

@Service
public class SerasaService  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ApiManagerTokenService apiTokenService;
	
	@Value("${serasa.username}")
	private String serasaUser;
	
	@Value("${serasa.password}") 
	private String serasaPassword;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SerasaService.class);
	
	private static final String SERASA_CONSULTA_API_URL = "/serasa/consulta/1.0/";
	
	public PessoaJuridica consultaSerasaCNPJ(String cnpj) throws SerasaConsultaException {
		
		try {
			
			DataLicensingServicePortType dataLicensingServiceSOAP = init();
			
			ParametersInPJ parametersInPJ = new ParametersInPJ();
			RetornoPJ retornoPJ = new RetornoPJ();
			parametersInPJ.setRetornoPJ(retornoPJ);
			retornoPJ.setRazaoSocial(Boolean.TRUE);
            retornoPJ.setDataAbertura(Boolean.TRUE);
            retornoPJ.setCnae(Boolean.TRUE);
            retornoPJ.setEndereco(Boolean.TRUE);
            retornoPJ.setTelefone(Boolean.TRUE);
            retornoPJ.setSituacaoCadastral(TipoConsulta.HISTORICO);
            retornoPJ.setRepresentanteLegal(true);
            retornoPJ.setSimplesNacional(TipoConsulta.HISTORICO);
            retornoPJ.setPacote(Pacote.PJ_1);            

			parametersInPJ.setCnpj(cnpj);
			
			
			final PessoaJuridica consultarPJ = dataLicensingServiceSOAP.consultarPJ(parametersInPJ);
			return consultarPJ;
		} catch (Exception e) {
			LOGGER.error("ERRO AO CONSULTAR SERASA",e);
			throw  new SerasaConsultaException(e);
		}		
	}

	private DataLicensingServicePortType init() throws ApiTokenException {
		final String serasaConsultaUrl = ConfigurationUtils.getURLGetToken().replaceAll("/token",SERASA_CONSULTA_API_URL);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL wsdlLocation = classloader.getResource("serasa.wsdl");
		final DataLicensingService service = new DataLicensingService(wsdlLocation,new QName("http://services.experian.com.br/DataLicensing/DataLicensingService/", "DataLicensingService"));
		DataLicensingServicePortType dataLicensingServiceSOAP = service.getDataLicensingServiceSOAP();
		BindingProvider prov = (BindingProvider)dataLicensingServiceSOAP;
		Map<String, List<String>> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", Arrays.asList( "Bearer "+apiTokenService.getToken()));
		prov.getRequestContext()
		.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				serasaConsultaUrl);
		prov.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
		@SuppressWarnings("rawtypes")
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(serasaUser, serasaPassword));
		prov.getBinding().setHandlerChain(handlerChain);
		return dataLicensingServiceSOAP;
	}
}
