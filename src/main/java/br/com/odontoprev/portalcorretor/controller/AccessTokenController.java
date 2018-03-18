package br.com.odontoprev.portalcorretor.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.exceptions.ApiTokenException;
import br.com.odontoprev.portalcorretor.model.ApiTokenResponse;
import br.com.odontoprev.portalcorretor.model.ApiTokenURL;
import br.com.odontoprev.portalcorretor.service.ApiManagerTokenService;

@Controller
public class AccessTokenController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ApiManagerTokenService token;
	
	@RequestMapping(method=RequestMethod.POST,value="/access_token")
	public ResponseEntity<ApiTokenResponse> getToken() {
		try {
			return ResponseEntity.ok(new ApiTokenResponse(token.getApiToken()));
		} catch (ApiTokenException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/access_token/url")
	public ResponseEntity<ApiTokenURL> getUrl() {
		return ResponseEntity.ok(new ApiTokenURL(ConfigurationUtils.getURLGetToken().replaceAll("/token", "")));
	}

}
