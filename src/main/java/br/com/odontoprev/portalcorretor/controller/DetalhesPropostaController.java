package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Beneficiarios;
import br.com.odontoprev.portalcorretor.model.DetalhesPropostaResponse;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DetalhesPropostaController {

    @Autowired
    PropostaService propostaService;

    @RequestMapping(value = "detalhesProposta", method = RequestMethod.GET)
    public ModelAndView detalhesProposta(Model model, @RequestParam("cdVenda") String cdVenda, HttpSession session) {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        DetalhesPropostaResponse detalhesProposta = propostaService.detalhesProposta(cdVenda);

        List<Beneficiarios> dadosTitulares = detalhesProposta.getVenda().getTitulares();

        for (Beneficiarios beneficiario : dadosTitulares) {
            Integer cdTitular = beneficiario.getCdTitular();
            if (cdTitular == 0) {

                //Titular
                model.addAttribute("cdTitular", beneficiario.getCdTitular());
                model.addAttribute("celular", beneficiario.getCelular());
                model.addAttribute("cpf", beneficiario.getCpf());
                model.addAttribute("cnpj", beneficiario.getCnpj());
                model.addAttribute("dataNasc", beneficiario.getDataNascimento());
                model.addAttribute("email", beneficiario.getEmail());
                model.addAttribute("nome", beneficiario.getNome());
                model.addAttribute("nomeMae", beneficiario.getNomeMae());
                model.addAttribute("pfPj", beneficiario.getPfPj());
                if (beneficiario.getSexo().equals("f")) {
                    model.addAttribute("sexo", "Feminino");
                } else {
                    model.addAttribute("sexo", "Masculino");
                }
                model.addAttribute("cdPlano", beneficiario.getCdPlano());
                model.addAttribute("cdVenda", beneficiario.getCdVenda());

                //Se dados bancarios for null ou vazio -->>> forma de pgmto é boleto senão debito em conta
                if (beneficiario.getDadosBancarios() == null) {
                    //tipo pgmto
                    model.addAttribute("tipoPgmto", "Boleto");
                } else {
                    model.addAttribute("tipoPgmto", "Débito em Conta");
                }

                //Endereço titular
                model.addAttribute("cep", beneficiario.getEndereco().getCep());
                model.addAttribute("logradouro", beneficiario.getEndereco().getLogradouro());
                model.addAttribute("numero", beneficiario.getEndereco().getNumero());
                if (beneficiario.getEndereco().getComplemento() == null) {
                    model.addAttribute("complemento", "Não há complemento");
                } else {
                    model.addAttribute("complemento", beneficiario.getEndereco().getComplemento());
                }
                model.addAttribute("bairro", beneficiario.getEndereco().getBairro());
                model.addAttribute("cidade", beneficiario.getEndereco().getCidade());
                model.addAttribute("estado", beneficiario.getEndereco().getEstado());
            } else {
                if (cdTitular == beneficiario.getCdTitular()) {
                    detalhesProposta.setDependentes(dadosTitulares);
                }
            }
        }

        //Lista dependentes
        List<Beneficiarios> listDependentes = detalhesProposta.getDependentes();
        if (listDependentes == null) {
            model.addAttribute("depNome", "Não há dependentes");
        }

        //Criticas
        model.addAttribute("criticas", detalhesProposta.getVenda().getCriticas());

        //Detalhes plano
        model.addAttribute("titulo", detalhesProposta.getVenda().getPlano().getTitulo());
        model.addAttribute("valor", detalhesProposta.getVenda().getPlano().getValor());
        model.addAttribute("valorTotal", dadosTitulares.size() * detalhesProposta.getVenda().getPlano().getValor());

        String tipoPlano = detalhesProposta.getVenda().getPlano().getTipo();
        if (tipoPlano.equals("2")) {
            model.addAttribute("tipoPlano", "Anual");
        } else {
            model.addAttribute("tipoPlano", "Mensal");
        }

        return new ModelAndView("resumo_pf_proposta_detalhes", "detalhesPlano", detalhesProposta);
    }

}
