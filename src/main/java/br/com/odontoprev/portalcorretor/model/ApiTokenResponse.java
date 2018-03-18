package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;

import br.com.odontoprev.api.manager.client.token.ApiToken;

public class ApiTokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String access_token;

	public ApiTokenResponse(ApiToken apiToken) {
		this.access_token = apiToken.getAccessToken();
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	

}
