package br.com.odontoprev.portalcorretor.controller.forcavenda;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.odontoprev.portalcorretor.controller.ForcaVendaController;

//201809281900 - esert - COR-849 : WEB - ConfigTest para ForcaVendaControllerTest.java
@Configuration
@ComponentScan(
	    value = "br.com.odontoprev.portalcorretor.controller",	    		
	    useDefaultFilters = false,
	    includeFilters = {
	        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ForcaVendaController.class)
	    })


@EnableWebMvc
public class ForcaVendaControllerConfigTest {

}
