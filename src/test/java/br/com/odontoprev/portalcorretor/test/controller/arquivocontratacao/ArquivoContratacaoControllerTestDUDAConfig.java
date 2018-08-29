package br.com.odontoprev.portalcorretor.test.controller.arquivocontratacao;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.odontoprev.portalcorretor.controller.DetalhesPropostaController;
import br.com.odontoprev.portalcorretor.service.PropostaService;

//201808241601 - esert - COR-620 tdd para serv disponibiliza pdf pme
//Classe responsável por configurar objetos que queremos mockar
@Configuration
@ComponentScan(
	    value = "br.com.odontoprev.portalcorretor.controller",
	    useDefaultFilters = false,
	    includeFilters = {
	        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DetalhesPropostaController.class)
	    })

@EnableWebMvc
public class ArquivoContratacaoControllerTestDUDAConfig {

	 //Mocando service que injetado no Controller
	 @Bean
	 @Primary
	 @Scope("application")
     public PropostaService service() { 
		 return Mockito.mock(PropostaService.class); 
	}
	 
	 
}
