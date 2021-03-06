package br.com.odontoprev.portalcorretor.controller.admin;

import br.com.odontoprev.portalcorretor.model.VincularDCMSModel;
import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.CnpjDadosDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaDcms;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class VincularDCMSController {

    @Autowired
    private EmpresaService empresaService;

    @RequestMapping("admin/update_dcms")
    public ModelAndView index() {
        return new ModelAndView("admin/update_dcms", "banana", new VincularDCMSModel());
    }

    @RequestMapping(value = "buscaCnpjDCMS", method = RequestMethod.POST)
    public ModelAndView buscaCnpjDCMS(Model model, HttpSession session, @ModelAttribute("banana") br.com.odontoprev.portalcorretor.model.VincularDCMSModel vincularDCMSModel) {

        String cnpj = vincularDCMSModel.getCnpj();

        if (
                cnpj == null
                        ||
                        cnpj.isEmpty()
                ) {
            vincularDCMSModel.setObservacao("O campo CNPJ é obrigatório");
        } else {

            cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");

            CnpjDadosDCMSResponse cnpjDadosDCMSResponse = empresaService.obterDadosEmpresaDCMS(cnpj);

            if (cnpjDadosDCMSResponse != null) {
                vincularDCMSModel.setCnpj(cnpjDadosDCMSResponse.getCnpj());
                vincularDCMSModel.setCdEmpresa(cnpjDadosDCMSResponse.getCdEmpresa());
                vincularDCMSModel.setCdEmpDcms(cnpjDadosDCMSResponse.getEmpDcms());
                vincularDCMSModel.setObservacao(cnpjDadosDCMSResponse.getObservacao());
            } else {
                vincularDCMSModel.setObservacao("Dados não encontrados.");
            }

            if (cnpjDadosDCMSResponse.getEmpDcms() == null) {
                vincularDCMSModel.setCdEmpDcms("");
            }
        }

        model.addAttribute("cnpj", vincularDCMSModel.getCnpj());
        model.addAttribute("cdEmpresa", vincularDCMSModel.getCdEmpresa());
        model.addAttribute("cdEmpDcms", vincularDCMSModel.getCdEmpDcms());
        model.addAttribute("observacao", vincularDCMSModel.getObservacao());
        return new ModelAndView("admin/update_dcms", "banana", vincularDCMSModel);
    }

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
                vincularDCMSModel.getCdEmpDcms() == null
                        ||
                        vincularDCMSModel.getCdEmpDcms().isEmpty()
                ) {
            vincularDCMSModel.setObservacao("O campo EmpDcms é obrigatório");
        } else {

            cnpj = vincularDCMSModel.getCnpj().replace(".", "").replace("/", "").replace("-", "").replace(" ", "");

            EmpresaDcms empresaDcms = new EmpresaDcms();
            empresaDcms.setCnpj(cnpj);
            empresaDcms.setCdEmpresa(vincularDCMSModel.getCdEmpresa());
            empresaDcms.setEmpDcms(vincularDCMSModel.getCdEmpDcms());

            EmpresaResponse empresaResponse = empresaService.updateDadosEmpresaDCMS(empresaDcms);

            if (empresaResponse != null) {
                vincularDCMSModel.setCnpj(vincularDCMSModel.getCnpj());
                vincularDCMSModel.setCdEmpresa(vincularDCMSModel.getCdEmpresa());
                vincularDCMSModel.setCdEmpDcms(vincularDCMSModel.getCdEmpDcms());
                vincularDCMSModel.setObservacao(empresaResponse.getMensagem().concat(" [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date().getTime())) + "]")); //201805171955 - esert
            } else {
                vincularDCMSModel.setObservacao("Falha na atualização ou geração TXT. [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date().getTime())) + "]");
            }
        }

        return new ModelAndView("admin/update_dcms", "banana", vincularDCMSModel);
    }

}
