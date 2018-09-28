package br.com.odontoprev.portalcorretor.controller.detalhesProposta;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.odontoprev.portalcorretor.controller.DetalhesPropostaController;

//201809281757 - esert - COR-847 : WEB - ConfigTest para DetalhesPropostaControllerTest.java
@Configuration
@ComponentScan(
	    value = "br.com.odontoprev.portalcorretor.controller",	    		
	    useDefaultFilters = false,
	    includeFilters = {
	        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DetalhesPropostaController.class)
	    })

@EnableWebMvc
public class DetalhesPropostaControllerConfigTest {

}
