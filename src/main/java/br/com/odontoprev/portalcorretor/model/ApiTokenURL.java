package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;

public class ApiTokenURL implements Serializable {

	private String url;

	public ApiTokenURL(String urlGetToken) {
		this.url = urlGetToken;	
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
