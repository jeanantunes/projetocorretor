package br.com.odontoprev.portalcorretor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.Cadastro;
import br.com.odontoprev.portalcorretor.model.Dependente;
import br.com.odontoprev.portalcorretor.model.VendaPf;

@Controller
public class VendaPfController {
	
	private static final String URL_ESTADO = "https://api.odontoprev.com.br:8243/cep/1.1/estados";
	
	
	@RequestMapping(value = "venda/pf/escolhaPlanoPF", method = RequestMethod.GET)
    public ModelAndView escolhaDoPlanoPf() {
    
	List<String> estados = 	new ArrayList<>();
	List<Dependente> dependentes = 	new ArrayList<>();
	
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
    	vendaPf.setComplementoTitularPlano("cc123");
    	vendaPf.setCidadeTitularPlano("Araraquara");
    	vendaPf.setBairroTitularPlano("Centro");
    	vendaPf.setEstado(estados);
    	vendaPf.setDependente(dependentes);

    	 return new ModelAndView("venda/pf/escolhaPlanoPF", "vendaPf", vendaPf);
    }
	
	
	@RequestMapping(value = "venda/pf/titularDoPlanoPF", method = RequestMethod.GET)
    public ModelAndView titularDoPlanoPf() {
    
	List<String> estados = 	new ArrayList<>();
	List<Dependente> dependentes = 	new ArrayList<>();
	
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
    	vendaPf.setComplementoTitularPlano("cc123");
    	vendaPf.setCidadeTitularPlano("Araraquara");
    	vendaPf.setBairroTitularPlano("Centro");
    	vendaPf.setEstado(estados);
    	vendaPf.setDependente(dependentes);

    	 return new ModelAndView("venda/pf/titularDoPlanoPF", "vendaPf", vendaPf);
    }
	

    @RequestMapping(value = "venda/pf/cnpj", method = RequestMethod.GET)
    public ResponseEntity<Cadastro> cnpj(@RequestParam("cnpj") String cnpj) {

        Cadastro cadastro = new Cadastro();
        cadastro.setCnpj(cnpj);
        cadastro.setRazaoSocial("OdontoPrev");
        cadastro.setCnae("123-12");
        cadastro.setSimplesNacional("Não");
        cadastro.setDataAbertura("01/01/2001");
        //cadastro.setStatusCnpj("VALIDO");
        cadastro.setNomeRepresentanteLegal("Representante Legal");
        cadastro.setCpfRepresentanteLegal("123.123.123-34");
        cadastro.setTelefone("(11)2222-1234");
        cadastro.setCelular("(11)98765-1234");
        cadastro.setEmail("teste@odontoprev.com.br");
        return cnpj.equals("23.423.423/423") ? ResponseEntity.ok(cadastro) : ResponseEntity.notFound().build();
    }
    
    @RequestMapping(value = "venda/pf/pagamentoDebitoCC", method = RequestMethod.POST)
    public ModelAndView pagamentoDebito() {
    	
    	VendaPf vendaPf = new VendaPf();
    		vendaPf.setPagamentoCartaoNome("0043");
    		vendaPf.setPagamentoCartaoValidade("2018");
        
    	return new ModelAndView("venda/pf/pagamentoDebitoCC", "vendaPf", vendaPf);
    }
    
    @RequestMapping(value = "venda/pf/compraRealizadaPF", method = RequestMethod.GET)
    public ModelAndView compraRealizada(@PathVariable("cadastro") VendaPf idVenda){
     	VendaPf vendaPf = new VendaPf();	
    return new ModelAndView("venda/pf/compraRealizadaPF", "vendaPf", vendaPf);
    }
    
    @RequestMapping(value = "venda/pf/cep", method = RequestMethod.GET)
    public ModelAndView cep(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("cadastro/home", "cadastro", cadastro);
    }
    
    @RequestMapping(value = "venda/pf/cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/cadastrar/termosCondicoes", "cadastro", cadastro);
    }

    @RequestMapping(value = "venda/pf/termosCondicoes", method = RequestMethod.POST)
    public ModelAndView termosCondicoes(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/bemvindo", "cadastro", cadastro);
    }

    @RequestMapping(value = "venda/pf/bemvindo", method = RequestMethod.POST)
    public ModelAndView bemvindo(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("venda/pf/bemvindo", "cadastro", cadastro);
    }  

}
