package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Cadastro;
import br.com.odontoprev.portalcorretor.model.VendaPf;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VendaPfController {

    @RequestMapping(value = "/venda/pf", method = RequestMethod.GET)
    public ModelAndView home() {
        
    	VendaPf vendaPf = new VendaPf();
    	vendaPf.setCelularTitularPlano("(11) 11111-1111");
    	vendaPf.setCepTitularPlano("15900-000");
    	vendaPf.setCpfTitularPlano("000.000.000-11");
    	vendaPf.setDataNascimentoTitularPlano("03/02/1993");
    	vendaPf.setEmailTitularPlano("teste@teste.com.br");
    	vendaPf.setEnderecoTitularPlano("Rua Siqueira Campos");
    	vendaPf.setNomeMaeTitularPlano("Cristina da Silva");
    	vendaPf.setNomeTitularPlano("Marco Da Silva");
    	vendaPf.setNumeroTitularPlano("12312313");
    	vendaPf.setSexo("male");
    	
        return new ModelAndView("/venda/pf/titularDoPlanoPF", "vendaPf", vendaPf);
    }

	@RequestMapping(value = "/cadastrarVendaPf", method = RequestMethod.POST)
	public ModelAndView cadastrarVendaPf(@ModelAttribute("vendaPf") VendaPf vendaPf) {
		return new ModelAndView("venda/pf/compraRealizadaPF", "vendaPf", vendaPf);
	}



}
