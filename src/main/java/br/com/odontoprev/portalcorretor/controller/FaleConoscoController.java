package br.com.odontoprev.portalcorretor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.FaleConoscoForcaVendasModel;
import br.com.odontoprev.portalcorretor.sendmail.SendMail;

@Controller
public class FaleConoscoController {
<<<<<<< HEAD

	@Autowired
	SendMail sendMail;

    @RequestMapping("/fale-conosco")
=======
    @RequestMapping("fale-conosco")
>>>>>>> a74a8382ca2688aa4c30eff604818e1b2b872190
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("fale-conosco");
        return modelAndView;
    }

    @RequestMapping("/fale-conosco-duvidas")
    public ModelAndView mensagemDuvidas() {
    	FaleConoscoForcaVendasModel faleConoscoForcaVendasModel = new FaleConoscoForcaVendasModel();
    	faleConoscoForcaVendasModel.setNomeCorretor("Corretor XYZ 1850");
    	faleConoscoForcaVendasModel.setNomeCorretora("Corretora XPTO 1850");
    	faleConoscoForcaVendasModel.setTipoMensagem("D�vidas");
        ModelAndView modelAndView = new ModelAndView(
        		"fale-conosco-mensagem", 
        		"faleConoscoForcaVendasModel", 
        		faleConoscoForcaVendasModel
        		);
        return modelAndView;
    }

    @RequestMapping("/fale-conosco-sugestoes")
    public ModelAndView mensagemSugestoes() {
    	FaleConoscoForcaVendasModel faleConoscoForcaVendasModel = new FaleConoscoForcaVendasModel();
    	faleConoscoForcaVendasModel.setNomeCorretor("Corretor XYZ 1850");
    	faleConoscoForcaVendasModel.setNomeCorretora("Corretora XPTO 1850");
    	faleConoscoForcaVendasModel.setTipoMensagem("Suget�es");
        ModelAndView modelAndView = new ModelAndView(
        		"fale-conosco-mensagem", 
        		"faleConoscoForcaVendasModel", 
        		faleConoscoForcaVendasModel
        		);
        return modelAndView;
    }

    @RequestMapping("/fale-conosco-suporte")
    public ModelAndView mensagemSuporte() {
    	FaleConoscoForcaVendasModel faleConoscoForcaVendasModel = new FaleConoscoForcaVendasModel();
    	faleConoscoForcaVendasModel.setNomeCorretor("Corretor XYZ 1850");
    	faleConoscoForcaVendasModel.setNomeCorretora("Corretora XPTO 1850");
    	faleConoscoForcaVendasModel.setTipoMensagem("Suporte");
        ModelAndView modelAndView = new ModelAndView(
        		"fale-conosco-mensagem", 
        		"faleConoscoForcaVendasModel", 
        		faleConoscoForcaVendasModel
        		);
        return modelAndView;
    }
    
    @RequestMapping(value = "/fale-conosco-mensagem", method = RequestMethod.POST)
    public ModelAndView salvar(@ModelAttribute("faleConoscoForcaVendasModel") br.com.odontoprev.portalcorretor.model.FaleConoscoForcaVendasModel faleConoscoForcaVendasModel) {
        
    	System.out.println(faleConoscoForcaVendasModel.toString());
    	
    	sendMail.sendMail(faleConoscoForcaVendasModel);
        
        return new ModelAndView("fale-conosco");
    }
}
