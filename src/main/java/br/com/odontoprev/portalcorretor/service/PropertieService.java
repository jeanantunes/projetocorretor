package br.com.odontoprev.portalcorretor.service;

import java.io.Serializable;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${odontoprev.servicebase.url}")
	private String baseURL;

	@Value("${odontoprev.corretor.metodo}")
	private String metodo;

	private Logger logger;

	//201808092006 - esert - COR-413:(Implementar Propertie util para o front)
	public String getPropertie(String propertieName) {

		try {
			return (new Properties()).getProperty(propertieName) ;
		} catch (Exception ex) {
			logger.error("getPropertie:ERRO:[{}]", ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

}
