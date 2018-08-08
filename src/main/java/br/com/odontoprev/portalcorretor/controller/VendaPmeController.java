package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.converter.ConverterCarrinhoForm;
import br.com.odontoprev.portalcorretor.exceptions.SerasaConsultaException;
import br.com.odontoprev.portalcorretor.model.*;
import br.com.odontoprev.portalcorretor.service.EnderecoService;
import br.com.odontoprev.portalcorretor.service.SerasaService;
import br.com.odontoprev.portalcorretor.service.VendaPMEService;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.*;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.Endereco;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.odontoprev.portalcorretor.service.dto.Plano.Integral_DOC_LE;
import static br.com.odontoprev.portalcorretor.service.dto.Plano.Master_LALE;

@Controller
public class VendaPmeController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VendaPmeController.class);

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private VendaPMEService vendaPMEService;

    @Autowired
    private SerasaService serasaService;

    @RequestMapping(value = "venda/pme/Escolha_um_plano")
    public ModelAndView escolhaPlanoPme(HttpSession session) throws IOException {
        Carrinho carrinho = new Carrinho();
        session.setAttribute("carrinho", carrinho);
        return new ModelAndView("venda/pme/1_Escolha_um_plano", "carrinho", carrinho);
    }

    @RequestMapping(value = "venda/pme/Escolha_um_plano/addPlanoSelecionadoPme/{nomePlano}")
    public ModelAndView planoSelecionadoPme(@PathVariable("nomePlano") String nomePlano, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho.getPlanos().size() < 2) {

            if (nomePlano.equals(Integral_DOC_LE.getCdPlano()) && !carrinho.getPlanos().contains(Integral_DOC_LE)) {
                carrinho.getPlanos().add(Integral_DOC_LE);

            } else if (nomePlano.equals(Integral_DOC_LE.getCdPlano()) && carrinho.getPlanos().contains(Integral_DOC_LE)) {
                carrinho.getPlanos().add(Master_LALE);

            } else if (nomePlano.equals(Master_LALE.getCdPlano()) && !carrinho.getPlanos().contains(Master_LALE)) {
                carrinho.getPlanos().add(Master_LALE);

            } else if (nomePlano.equals(Master_LALE.getCdPlano()) && carrinho.getPlanos().contains(Master_LALE)) {
                carrinho.getPlanos().add(Integral_DOC_LE);
            }

        }
        return new ModelAndView("venda/pme/2_Plano_selecionado", "carrinho", carrinho);
    }

    @RequestMapping(value = "venda/pme/Escolha_um_plano/addPlanoSelecionadoPme")
    public ModelAndView planoSelecionadoPme(HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho.getPlanos().size() < 2) {
            if (carrinho.getPlanos().contains(Integral_DOC_LE)) {
                carrinho.getPlanos().add(Master_LALE);
            } else {
                carrinho.getPlanos().add(Integral_DOC_LE);
            }
        }
        return new ModelAndView("venda/pme/2_Plano_selecionado", "carrinho", carrinho);
    }


    @RequestMapping(value = "venda/pme/Escolha_um_plano/deletePlanoSelecionadoPme")
    public ModelAndView removePlano(@PathVariable("nomePlano") String nomePlano, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");


        carrinho.getPlanos().remove(carrinho.getPlanos().size() - 1);

        return new ModelAndView("venda/pme/2_Plano_selecionado", "vendaPme", new VendaPme());
    }

    @RequestMapping(value = "/venda/pme/Escolha_um_plano/AddBeneficiarioDependente", method = RequestMethod.POST)
    public ModelAndView addPlanoBeneficiarioDependente(Carrinho carrinhoForm, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        carrinho.getBeneficiarios().add(new Beneficiario());
        carrinho.setVendaPme(carrinhoForm.getVendaPme());
        return new ModelAndView("venda/pme/3_Add_beneficiario_dependente", "carrinho", carrinho);
    }

    @RequestMapping(value = "/venda/pme/Escolha_um_plano/confirmacaoProposta", method = RequestMethod.POST, params = "action=add")
    public ModelAndView savePlanoBeneficiarioDependente(Carrinho carrinhoForm, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        carrinho.setBeneficiarios(carrinhoForm.getBeneficiarios());
        carrinho.getBeneficiarios().add(new Beneficiario());
        return new ModelAndView("venda/pme/3_Add_beneficiario_dependente", "carrinho", carrinho);
    }


    @RequestMapping(value = "/venda/pme/Escolha_um_plano/confirmacaoProposta", method = RequestMethod.POST, params = "action=save")
    public ModelAndView confirmacaoProposta(Carrinho carrinhoForm, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        carrinho.setBeneficiarios(carrinhoForm.getBeneficiarios());

        vendaPMEService.vender(new ConverterCarrinhoForm().converter(carrinho, (UsuarioSession) session.getAttribute("usuario")));
        return new ModelAndView("venda/pme/4_confirmacao_proposta_pme");
    }
    // Fim -> Metodos de Fluxo de Tela


    @RequestMapping(value = "buscaCnpjPme")
    public ModelAndView buscaCnpjPme(@RequestParam("cnpj") String cnpj, HttpSession session) {
        VendaPme vendaPme = new VendaPme();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        try {
            final PessoaJuridica pessoaJuridica = serasaService.consultaSerasaCNPJ(cnpj);
            vendaPme.setCnpj(pessoaJuridica.getCnpj());
            //TODO: Verificar
            //vendaPme.setInscricaoEstadual("");
            //vendaPme.setRamoDeAtividade(pessoaJuridica.);
            vendaPme.setRazaoSocial(pessoaJuridica.getRazaoSocial());
            vendaPme.setNomeFantasia(pessoaJuridica.getNomeFantasia());
            List<RepresentanteLegal> representantesLegais = pessoaJuridica.getRepresentanteLegal().getRepresentanteLegal();
            if (!representantesLegais.isEmpty()) {
                RepresentanteLegal representanteLegal = representantesLegais.get(0);
                if (representanteLegal != null) {
                    vendaPme.setRepresentanteLegal(representanteLegal.getNome());
                    vendaPme.setCpf(representanteLegal.getDocumento());
                }
            }
            vendaPme.setRepresentanteEhContatoEmpresa(false);
            List<ArrayOfTelefone> telefonesList = pessoaJuridica.getTelefones();
            if (!telefonesList.isEmpty()) {
                ArrayOfTelefone telefones = telefonesList.get(0);
                if (telefonesList.isEmpty()) {

                    vendaPme.setTelefone("");
                    vendaPme.setCelular("");

                }
            }
            List<ArrayOfEndereco> enderecosList = pessoaJuridica.getEnderecos();
            if (!enderecosList.isEmpty()) {
                ArrayOfEndereco enderecos = enderecosList.get(0);
                List<Endereco> endereco = enderecos.getEndereco();
                if (endereco != null && endereco.get(0) != null) {
                    vendaPme.setCep(endereco.get(0).getCep());
                    vendaPme.setEndereco(endereco.get(0).getLogradouro().getNome());
                    vendaPme.setNumero(endereco.get(0).getLogradouro().getNumero());
                    vendaPme.setComplemento(endereco.get(0).getLogradouro().getComplemento());
                    vendaPme.setBairro(endereco.get(0).getBairro());
                    vendaPme.setCidade(endereco.get(0).getCidade());
                }
            }
            vendaPme.setEnderecoEhoMesmo(false);
        } catch (SerasaConsultaException e) {
            LOGGER.error("ERROR", e);
        }
        //vendaPme = mockDados();
        final Map<String, Object> model = new HashMap<String, Object>();
        carrinho.setVendaPme(vendaPme);
        model.put("carrinho", carrinho);

        return new ModelAndView("venda/pme/2_Plano_selecionado", model);
    }


    private Dependente mockDadosDependente() {
        Dependente dependente = new Dependente();
        dependente.setNome("Filho Roberto Silva");
        dependente.setCpf("296.134.489.76");
        dependente.setDataNascimento("10/10/1959");
        dependente.setNomeDaMae("Maria Mae do Filho");
        dependente.setSexo("M");
        dependente.setPlano("2");

        return dependente;
    }

    private Beneficiario mockDadosBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome("Roberto Silva");
        beneficiario.setCpf("896.154.589.96");
        beneficiario.setDataNascimento("10/10/1959");
        beneficiario.setNomeMae("Maria Mae do Roberto");
        beneficiario.setSexo("M");
        beneficiario.setCdPlano(2l);

        return beneficiario;
    }

    private VendaPme mockDados() {
        VendaPme vendaPme = new VendaPme();
        vendaPme.setCnpj("23.423.423/423");
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
        vendaPme.setEnderecoEhoMesmo(true);

        return vendaPme;
    }
}
