package br.com.odontoprev.portalcorretor.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.exceptions.SerasaConsultaException;
import br.com.odontoprev.portalcorretor.service.SerasaService;




@Controller
public class SerasaTesteController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SerasaTesteController.class);
	
	
	@Autowired
	private SerasaService service;
	

	@RequestMapping(method=RequestMethod.GET,value="/serasa/consulta/{cnpj}")
	public ModelAndView serasaConsultaGet(@PathVariable String cnpj) {
		try {
			service.consultaSerasaCNPJ(cnpj);
		} catch (SerasaConsultaException e) {
			LOGGER.error("ERROR",e);
		}
		return new ModelAndView("home");
	}
}
