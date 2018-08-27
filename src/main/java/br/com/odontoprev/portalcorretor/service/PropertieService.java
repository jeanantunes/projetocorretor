package br.com.odontoprev.portalcorretor.service;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertieService implements Serializable {

	/**
	 *
	 */

	@Autowired
	private Environment env;

	private static final long serialVersionUID = 1L;

	private Logger logger;

	//201808092006 - esert - COR-413:(Implementar Propertie util para o front)
	public String getPropertie(String propertieName) {

		String property = null;
		try {

			property = env.getProperty(propertieName);

		} catch (Exception e){
			logger.error(String.format("Erro ao ler propertie. Mensagem: [%s]", e.toString()));
		}

		return property;
	}

}
