package br.com.odontoprev.portalcorretor.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.VincularDCMSModel;
import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.CnpjDadosDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaDcms;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;

@Controller
public class VincularDCMSController {
	
	@Autowired
    private EmpresaService empresaService;

	@RequestMapping("admin/update_dcms")
    public ModelAndView index() {
	    return new ModelAndView("admin/update_dcms", "banana", new VincularDCMSModel()); //201805161823 - esert - nao pode passar model (null), precisa passar model instanciada 
    }

//	@RequestMapping(value = "admin/buscaCnpjDCMS/{cnpj}", method = RequestMethod.GET)
//	public ModelAndView buscaCnpjDCMS(@PathVariable String cnpj) {
	
	@RequestMapping(value = "buscaCnpjDCMS", method = RequestMethod.POST)
	public ModelAndView buscaCnpjDCMS(HttpSession session, @ModelAttribute("banana") br.com.odontoprev.portalcorretor.model.VincularDCMSModel vincularDCMSModel) {

		//VincularDCMSModel vincularDCMSModel = new VincularDCMSModel();
		String cnpj = vincularDCMSModel.getCnpj();
		
//		UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");	    
//	    vincularDCMSModel.setCnpj("11431155000107");
	        
	    if (
	    	cnpj == null 
	    	|| 
	    	cnpj.isEmpty()
	    ) {
	    	vincularDCMSModel.setObservacao("O campo CNPJ é obrigatório");
	    } else {
	    
		    cnpj = cnpj.replace(".","").replace("/","").replace("-","");
		    
		    CnpjDadosDCMSResponse cnpjDadosDCMSResponse = empresaService.obterDadosEmpresaDCMS(cnpj);
		
		    if(cnpjDadosDCMSResponse!=null) {
			    vincularDCMSModel.setCnpj(cnpjDadosDCMSResponse.getCnpj());
			    vincularDCMSModel.setCdEmpresa(cnpjDadosDCMSResponse.getCdEmpresa());
			    vincularDCMSModel.setEmpDcms(cnpjDadosDCMSResponse.getEmpDcms());
			    vincularDCMSModel.setObservacao(cnpjDadosDCMSResponse.getObservacao());
		    } else {
		    	vincularDCMSModel.setObservacao("Dados não encontrados.");    	
		    }
	    }
	    
	    return new ModelAndView("admin/update_dcms", "banana", vincularDCMSModel);	
	}

	//201805171846 - esert - COR-170
	@RequestMapping(value = "vincularDCMS", method = RequestMethod.POST)
	public ModelAndView vincularDCMS(HttpSession session, @ModelAttribute("banana") br.com.odontoprev.portalcorretor.model.VincularDCMSModel vincularDCMSModel) {
		    
	    String cnpj = "0";	    	    
	    if (
	    	vincularDCMSModel.getCnpj() == null 
	    	|| 
	    	vincularDCMSModel.getCnpj().isEmpty()
	    ) {
	    	vincularDCMSModel.setObservacao("O campo CNPJ é obrigatório");
	    } else if (
	    	vincularDCMSModel.getCdEmpresa() == null 
	    	|| 
	    	vincularDCMSModel.getCdEmpresa() == 0L
	    ) {
	    	vincularDCMSModel.setObservacao("O campo CdEmpresa é obrigatório");	    	
	    } else if (
			vincularDCMSModel.getEmpDcms() == null 
			|| 
			vincularDCMSModel.getEmpDcms().isEmpty()
	    ) {
	    	vincularDCMSModel.setObservacao("O campo EmpDcms é obrigatório");	    	
	    } else {
	    
		    cnpj = vincularDCMSModel.getCnpj().replace(".","").replace("/","").replace("-","").replace(" ","");
		    
		    EmpresaDcms empresaDcms = new EmpresaDcms();
		    empresaDcms.setCnpj(cnpj);
		    empresaDcms.setCdEmpresa(vincularDCMSModel.getCdEmpresa());
		    empresaDcms.setEmpDcms(vincularDCMSModel.getEmpDcms());
		    
		    EmpresaResponse empresaResponse = empresaService.updateDadosEmpresaDCMS(empresaDcms);
		
		    if(empresaResponse!=null) {
			    vincularDCMSModel.setCnpj(vincularDCMSModel.getCnpj());
			    vincularDCMSModel.setCdEmpresa(vincularDCMSModel.getCdEmpresa());
			    vincularDCMSModel.setEmpDcms(vincularDCMSModel.getEmpDcms());
			    vincularDCMSModel.setObservacao(empresaResponse.getMensagem().concat(" [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date().getTime())) + "]")); //201805171955 - esert
		    } else {
		    	vincularDCMSModel.setObservacao("Falha na atualização ou geração TXT. [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date().getTime())) + "]");    	
		    }
	    }
	    
	    return new ModelAndView("admin/update_dcms", "banana", vincularDCMSModel);	
	}
	
}
