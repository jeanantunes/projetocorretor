package br.com.odontoprev.portalcorretor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.odontoprev.api.manager.client.token.ApiManagerToken;
import br.com.odontoprev.api.manager.client.token.ApiManagerTokenFactory;
import br.com.odontoprev.api.manager.client.token.ApiToken;
import br.com.odontoprev.api.manager.client.token.enumerator.ApiManagerTokenEnum;
import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;

@Service
@Component
public class ApiManagerTokenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiManagerTokenService.class);

	public String getToken() throws ApiTokenException {
			
			final ApiManagerToken apiManager = ApiManagerTokenFactory.create(ApiManagerTokenEnum.WSO2, "PORTAL_CORRETOR_WEB");
			final ApiToken apiToken;
			try {
				apiToken = apiManager.generateToken();
				return apiToken.getAccessToken();
			} catch (Exception e) {
				LOGGER.error("ERRO AO OBTER TOKEN", e);
				throw new ApiTokenException("ERRO AO OBTER TOKEN",e);
			} 	
	}
	
	public ApiToken getApiToken() throws ApiTokenException {
		
		final ApiManagerToken apiManager = ApiManagerTokenFactory.create(ApiManagerTokenEnum.WSO2, "PORTAL_CORRETOR_WEB");
		final ApiToken apiToken;
		try {
			apiToken = apiManager.generateToken();
			return apiToken;
		} catch (Exception e) {
			LOGGER.error("ERRO AO OBTER TOKEN", e);
			throw new ApiTokenException("ERRO AO OBTER TOKEN",e);
		} 	
}
}
