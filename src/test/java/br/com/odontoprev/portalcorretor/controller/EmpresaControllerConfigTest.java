package br.com.odontoprev.portalcorretor.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(
	    value = "br.com.odontoprev.portalcorretor.controller",	    		
	    useDefaultFilters = false,
	    includeFilters = {
	        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = EmpresaController.class)
	    })
@EnableWebMvc
public class EmpresaControllerConfigTest {

}
