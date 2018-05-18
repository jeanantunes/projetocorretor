package br.com.odontoprev.portalcorretor.controller.admin;

import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import br.com.odontoprev.portalcorretor.model.ReenvioEmailAceitePMEModel;
import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Controller
public class ReenvioEmailAceitePMEController {

    @Autowired
    EmpresaService empresaService;

    @RequestMapping("admin/email_aceite")
    public String indexInfoPlanoIntegralDocLe() {
        return "admin/email_aceite";

    }

    @RequestMapping(value = "buscaCnpjReenvio", method = RequestMethod.POST)
    public ModelAndView buscarCnpjReenvio(Model model, @ModelAttribute("emailAceite") ReenvioEmailAceitePMEModel reenvioEmailAceitePMEModel) {

        if (reenvioEmailAceitePMEModel.getCnpj() == null || "".equals(reenvioEmailAceitePMEModel.getCnpj())) {
            reenvioEmailAceitePMEModel.setError("O campo CNPJ é obrigatório");
            model.addAttribute("error", reenvioEmailAceitePMEModel.getError());
            return new ModelAndView("admin/email_aceite", "reenvioEmailAceitePME", reenvioEmailAceitePMEModel);
        }
        String cnpj = reenvioEmailAceitePMEModel.getCnpj().replace(".", "").replace("/", "").replace("-", "");
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = empresaService.obterDadosReenvio(cnpj);

        if (cnpjDadosAceiteResponse.getCnpj() == null) {
            reenvioEmailAceitePMEModel.setObservacao("CNPJ não encontrado!!!");
            model.addAttribute("observacao", reenvioEmailAceitePMEModel.getObservacao());
            return new ModelAndView("admin/email_aceite", "reenvioEmailAceitePME", reenvioEmailAceitePMEModel);
        }
        reenvioEmailAceitePMEModel.setCnpj(cnpjDadosAceiteResponse.getCnpj());
        reenvioEmailAceitePMEModel.setRazaoSocial(cnpjDadosAceiteResponse.getRazaoSocial());
        if (cnpjDadosAceiteResponse.getTokenAceite() != null) {
            if (cnpjDadosAceiteResponse.getTokenAceite().getDataAceite() != null) {
                reenvioEmailAceitePMEModel.setDataAceite(new SimpleDateFormat("dd/MM/yyyy").format(cnpjDadosAceiteResponse.getTokenAceite().getDataAceite()));
            } else {
                reenvioEmailAceitePMEModel.setDataAceite(null);
            }
            if (cnpjDadosAceiteResponse.getTokenAceite().getEmail() != null) {
                reenvioEmailAceitePMEModel.setEmail(cnpjDadosAceiteResponse.getTokenAceite().getEmail());
            }
        }
        reenvioEmailAceitePMEModel.setObservacao(cnpjDadosAceiteResponse.getObservacao());

        model.addAttribute("cnpj", reenvioEmailAceitePMEModel.getCnpj());
        model.addAttribute("razaoSocial", reenvioEmailAceitePMEModel.getRazaoSocial());
        model.addAttribute("dataAceite", reenvioEmailAceitePMEModel.getDataAceite());
        model.addAttribute("email", reenvioEmailAceitePMEModel.getEmail());
        model.addAttribute("observacao", reenvioEmailAceitePMEModel.getObservacao());
        return new ModelAndView("admin/email_aceite", "reenvioEmailAceitePME", reenvioEmailAceitePMEModel);

    }

    @RequestMapping(value = "reenvioEmailAceite", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView reenvioEmailAceite(Model model, @ModelAttribute("reenvioEmailAceite") ReenvioEmailAceitePMEModel reenvioEmailAceitePMEModel) {

        String cnpj = reenvioEmailAceitePMEModel.getCnpj().replace(".", "").replace("/", "").replace("-", "");
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = empresaService.obterDadosReenvio(cnpj);

        reenvioEmailAceitePMEModel.setCdEmpresa(String.valueOf(cnpjDadosAceiteResponse.getCdEmpresa()));
        reenvioEmailAceitePMEModel.setCdVenda(String.valueOf(cnpjDadosAceiteResponse.getCdVenda()));
        cnpjDadosAceiteResponse.setEmail(reenvioEmailAceitePMEModel.getEmail());
        EmpresaResponse empresaResponse = empresaService.reenvioEmailAceite(cnpjDadosAceiteResponse);

        model.addAttribute("cdEmpresa", reenvioEmailAceitePMEModel.getCdEmpresa());
        model.addAttribute("cdVenda", reenvioEmailAceitePMEModel.getCdVenda());
        model.addAttribute("email", reenvioEmailAceitePMEModel.getEmail());
        if (empresaResponse == null) {
            reenvioEmailAceitePMEModel.setError("Erro ao atualizar/enviar e-mail.");
            model.addAttribute("error", reenvioEmailAceitePMEModel.getError());
        } else {
            model.addAttribute("observacao", empresaResponse.getMensagem());
        }
        return new ModelAndView("admin/email_aceite", "reenvioEmail", reenvioEmailAceitePMEModel);
    }
}
