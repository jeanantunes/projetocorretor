package br.com.odontoprev.portalcorretor.controller.admin;

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

@Controller
public class VincularDCMSController {
	
	@Autowired
    private EmpresaService empresaService;

	@RequestMapping("admin/update_dcms")
    public ModelAndView index() {
	    return new ModelAndView("admin/update_dcms", "banana", new VincularDCMSModel()); //201805161823 - esert - nao pode passar model (null), precisa passar model instanciada 
    }

	@RequestMapping(value = "admin/buscaCnpjDCMS/{cnpj}", method = RequestMethod.GET)
	public ModelAndView buscarCnpj(@ModelAttribute("cnpj") String cnpj) {
		VincularDCMSModel vincularDCMSModel = new VincularDCMSModel();
	
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

	@RequestMapping(value = "vincularDCMS", method = RequestMethod.POST)
		public ModelAndView vincularDCMS(HttpSession session, @ModelAttribute("banana") br.com.odontoprev.portalcorretor.model.VincularDCMSModel vincularDCMSModel) {
		
	//		UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
		    
		    String cnpj = "0";
	//	    vincularDCMSModel.setCnpj("11431155000107");
		    	    
		    if (
		    	vincularDCMSModel.getCnpj() == null 
		    	|| 
		    	vincularDCMSModel.getCnpj().isEmpty()
		    ) {
		    	vincularDCMSModel.setObservacao("O campo CNPJ é obrigatório");
		    } else {
		    
			    cnpj = vincularDCMSModel.getCnpj().replace(".","").replace("/","").replace("-","");
			    
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

}
