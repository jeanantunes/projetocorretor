package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Beneficiario;
import br.com.odontoprev.portalcorretor.model.Dependente;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VendaPmeController {
    @RequestMapping(value = "/vendapme/cnpj", method = RequestMethod.GET)
    public ResponseEntity<VendaPme> cnpj(@RequestParam("cnpj") String cnpj) {

        VendaPme vendaPme = new VendaPme();
        vendaPme.setCnpj(cnpj);
        vendaPme.setInscricaoEstadual("22536");
        vendaPme.setRamoDeAtividade("Administrativa");
        vendaPme.setRazaoSocial("OdontoPrev");
        vendaPme.setNomeFantasia("OdontoPrev Dental");
        vendaPme.setRepresentanteLegal("João OdontoPrev");
        vendaPme.setCpf("25698563261");
        vendaPme.setRepresentanteEhContatoEmpresa(true);
        vendaPme.setTelefone("25 33655865");
        vendaPme.setCelular("25 998563325");
        vendaPme.setEmail("teste@odontoprev.com.br");
        vendaPme.setCep("12575002");
        vendaPme.setEndereco("Rua da Odonto");
        vendaPme.setNumero("800");
        vendaPme.setComplemento("Bloco 2");
        vendaPme.setBairro("AlphaVille");
        vendaPme.setCidade("Barueri");
        vendaPme.setEstado("SP");
        vendaPme.setEnderecoEhoMesmo(true);
        vendaPme.setDataVencimento("15");

        return cnpj.equals("23.423.423/423") ? ResponseEntity.ok(vendaPme) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/cadastrarVendaPme", method = RequestMethod.POST)
    public ModelAndView cadastrarVendaPme(@ModelAttribute("vendaPme") VendaPme vendaPme) {
        return new ModelAndView("venda/pme/Add_beneficiario_dependente", "vendaPme", vendaPme);
    }

    @RequestMapping(value = "/cadastrarBeneficiario", method = RequestMethod.POST)
    public ModelAndView cadastrarBeneficiario(@ModelAttribute("beneficiario") Beneficiario beneficiario) {
        return new ModelAndView("venda/pme/confirmacao_proposta_pme", "beneficiario", beneficiario);
        //TODO Confirmar retorno
    }

    @RequestMapping(value = "/cadastrarDependente", method = RequestMethod.POST)
    public ModelAndView cadastrarDependente(@ModelAttribute("dependente") Dependente dependente) {
        return new ModelAndView("venda/pme/confirmacao_proposta_pme", "dependente", dependente);
        //TODO Confirmar retorno
    }
}
