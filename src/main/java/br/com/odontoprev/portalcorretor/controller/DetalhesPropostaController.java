package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.*;
import br.com.odontoprev.portalcorretor.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class DetalhesPropostaController {

    @Autowired
    PropostaService propostaService;

    FichaFinanceiraResponse fichaFinanciera;

    @RequestMapping(value = "detalhesProposta", method = RequestMethod.GET)
    public ModelAndView detalhesProposta(Model model, @RequestParam("cdVenda") String cdVenda, HttpSession session) throws IOException, ParseException {

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

        SimpleDateFormat dtIni = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dtFim = new SimpleDateFormat("yyyy-MM-dd");

        String dataInicial = dtIni.format((dtIni.parse("2018-03-01")));

        Date dtFinal = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtFinal);
        cal.set(Calendar.DAY_OF_MONTH, 1); // DIA 1
        cal.add(Calendar.MONTH, 1); // +1 MES
        cal.add(Calendar.YEAR, 1); // +1 ANO
        dtFinal = cal.getTime();
        String dataFinal = dtFim.format((dtFinal));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00-03:00");
        Date dtHj = new Date();
        String dataHoje = sdf.format(dtHj).replace(" ", "T");

        fichaFinanciera = detalhesBoleto(detalhesProposta.getVenda().getPropostaDcms(), dataInicial, dataFinal);

        detalhesProposta.setFichaFinanciera(new ArrayList<>());
        for (FichaFinanciera f : fichaFinanciera.getFichaFinanciera()) {
            if (f.getDataRenegociacao().compareTo(dataHoje) >= 0 &&
                    (f.getStatusPagamento().equals("RENEGOCIADO") ||
                            f.getStatusPagamento().equals("INCLUSAO DE TITULO"))) {
                detalhesProposta.getFichaFinanciera().add(f);
            }
        }

        if (detalhesProposta.getFichaFinanciera().size() > 0) {
            //detalhesProposta.setFichaFinanciera(fichaFinanciera.getFichaFinanciera());
            model.addAttribute("fichaFinanciera", detalhesProposta.getFichaFinanciera());
            model.addAttribute("codigoDoAssociado", detalhesProposta.getVenda().getPropostaDcms());
            model.addAttribute("dataInicial", dataInicial);
            model.addAttribute("dataFinal", dataFinal);
        } else {
            model.addAttribute("fichaFinanciera", new ArrayList<>());
        }


        return new ModelAndView("resumo_pf_proposta_detalhes", "detalhesPlano", detalhesProposta);
    }

    @RequestMapping(value = "detalhesBoleto")
    public FichaFinanceiraResponse detalhesBoleto(String detalhes, String dtIni, String dtFim) throws ParseException {

        DetalhesBoletoResponse boletoResponse = new DetalhesBoletoResponse();
        boletoResponse.setDataInicial(dtIni);
        boletoResponse.setDataFinal(dtFim);
        boletoResponse.setCodigo(detalhes);

        FichaFinanceiraResponse detalhesBoleto = propostaService.listarBoleto(boletoResponse);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00-03:00");
        Date dtHj = new Date();
        String dataHoje = sdf.format(dtHj).replace(" ", "T");

        if (detalhesBoleto != null) {
            List<FichaFinanciera> financieraList = detalhesBoleto.getFichaFinanciera();
            for (FichaFinanciera ficha : financieraList) {
                String dataRenegociacao = ficha.getDataRenegociacao();
                Integer temp = dataRenegociacao.compareTo(dataHoje);

                if (temp >= 0) {
                    ficha.getCompetencia();
                    ficha.getDataRenegociacao();
                    ficha.getDiasDeAtraso();
                    ficha.getFatura();
                    ficha.getIsencaoMultaJuros();
                    ficha.getMultaRescisoria();
                    ficha.getNotificacao();
                    ficha.getParcela();
                    ficha.getPercentualJuros();
                    ficha.getPercentualMulta();
                    ficha.getPermiteIsencaoMultaJuros();
                    ficha.getQuantidadeDiasAtraso();
                    ficha.getStatusPagamento();
                    ficha.getValorAtualJuros();
                    ficha.getValorAtualMulta();
                    ficha.getValorAtualParcelaCalculado();
                    ficha.getValorEstorno();
                    ficha.getValorJurosPago();
                    ficha.getValorMultaPago();
                    ficha.getValorNaoAplicado();
                    ficha.getValorPago();
                    ficha.getValorParcela();
                    ficha.getVencimentoOriginal();
                }
            }
        }
        return detalhesBoleto;
    }

    @RequestMapping(value = "downloadBoleto", method = {RequestMethod.GET})
    public void downloadBoleto(Model model, HttpServletResponse response,
                               @RequestParam(value = "id") String id) throws IOException, ParseException {

        SimpleDateFormat dtIni = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dtFim = new SimpleDateFormat("yyyy-MM-dd");

        String dataInicial = dtIni.format((dtIni.parse("2018-03-01")));
        Date dtFinal = new Date();
        String dataFinal = dtFim.format((dtFinal));

        String[] temp = id.split("-");
        //detalhesBoleto(temp[1], dataInicial, dataFinal);

        String idx = temp[0].substring(1);
        Integer t = Integer.valueOf(idx);
        FichaFinanciera financiera = fichaFinanciera.getFichaFinanciera().get(t);

        //Dados para download
        FichaFinancieraBoleto financieraBoleto = new FichaFinancieraBoleto();
        financieraBoleto.setCodigoDoAssociado(temp[1]);
        financieraBoleto.setDataVencimentoOriginal(financiera.getVencimentoOriginal());
        financieraBoleto.setNumeroParcela(financiera.getParcela());

        /*
        Date dataVencimento = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataVencimento);
        cal.add(Calendar.DATE, 5); // +5 DIAS
        dataVencimento = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00-03:00");
        financieraBoleto.setDataVencimento(sdf.format(dataVencimento).replace(" ", "T"));
        */

        financieraBoleto.setTipoBoleto("PDF");
        financieraBoleto.setCodigoSistema("0");
        financieraBoleto.setRealizarRenegociacao("N"); //TODO: Verificar regra de negócio para "S" e "N" @JeanAntunes

        byte[] file = propostaService.gerarBoleto(financieraBoleto);

        if (file == null) {
            model.addAttribute("error", "Não foi possível gerar boleto, porfavor tente novamente.");
        } else {
            response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", "boleto.pdf");
            response.setHeader(headerKey, headerValue);
            response.getWriter().write(new String(file, "UTF-8"));
            response.getWriter().flush();
        }
    }


}