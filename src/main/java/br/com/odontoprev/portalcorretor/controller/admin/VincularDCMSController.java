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
    public String index() {
        return "admin/update_dcms";
    }

	@RequestMapping(value = "buscarCnpj", method = RequestMethod.POST)
	public ModelAndView buscarCnpj(HttpSession session, @ModelAttribute("vincularDCMS") VincularDCMSModel vincularDCMSModel) {
	
//		UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
	    
	    String cnpj = "11431155000107";
	    
	    vincularDCMSModel.setCnpj(cnpj); //qg debug
	    
	    if (vincularDCMSModel.getCnpj() == null || "".equals(vincularDCMSModel.getCnpj())) {
	    	vincularDCMSModel.setObservacao("O campo CNPJ é obrigatório");
	        return new ModelAndView("update_dcms", "vincularDCMS", vincularDCMSModel);
	    }
	    
	    cnpj = vincularDCMSModel.getCnpj().replace(".","").replace("/","").replace("-","");
	    
	    CnpjDadosDCMSResponse cnpjDadosDCMSResponse = empresaService.obterDadosEmpresaDCMS(cnpj);
	
	    vincularDCMSModel.setCnpj(cnpjDadosDCMSResponse.getCnpj());
	    vincularDCMSModel.setCdEmpresa(cnpjDadosDCMSResponse.getCdEmpresa());
	    vincularDCMSModel.setEmpDcms(cnpjDadosDCMSResponse.getEmpDcms());
	    vincularDCMSModel.setObservacao(cnpjDadosDCMSResponse.getObservacao());
	
	    return new ModelAndView("admin/update_dcms", "vincularDCMS", vincularDCMSModel);
	
	}

}
