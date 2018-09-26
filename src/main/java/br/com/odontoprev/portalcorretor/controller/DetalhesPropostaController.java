package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.*;
import br.com.odontoprev.portalcorretor.service.PropostaService;
import br.com.odontoprev.portalcorretor.service.dto.BeneficiariosPropostaResponsePagination;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaPropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "detalhesPropostaPF", method = RequestMethod.GET)
    public ModelAndView detalhesPropostaPF(Model model, @RequestParam("cdVenda") String cdVenda) throws IOException, ParseException {

        PropostaCritica detalhesPropostaPF = propostaService.detalhesProposta(cdVenda);

        List<Beneficiario> dadosTitulares = detalhesPropostaPF.getVenda().getTitulares();

        for (Beneficiario beneficiario : dadosTitulares) {
            Long cdTitular = beneficiario.getCdTitular();
            if (cdTitular == null) {
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
                detalhesPropostaPF.getVenda().setTitulares(dadosTitulares);
            }
        }

        //Lista dependentes
        //List<Beneficiario> listDependentes = detalhesPropostaPF.getVenda().getTitulares();
        //if (listDependentes == null) {
        //    model.addAttribute("depNome", "Não há dependentes");
        //}

        //Criticas
        //model.addAttribute("criticas", detalhesPropostaPF.getVenda().getCriticas());

        //Detalhes plano
        model.addAttribute("titulo", detalhesPropostaPF.getVenda().getPlano().getTitulo());
        model.addAttribute("valor", detalhesPropostaPF.getVenda().getPlano().getValor());
        model.addAttribute("valorTotal", dadosTitulares.size() * detalhesPropostaPF.getVenda().getPlano().getValor());

        String tipoPlano = detalhesPropostaPF.getVenda().getPlano().getTipo();
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

        model.addAttribute("codigoDoAssociado", detalhesPropostaPF.getVenda().getPropostaDcms());
        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        fichaFinanciera = detalhesBoleto(detalhesPropostaPF.getVenda().getPropostaDcms(), dataInicial, dataFinal);

        if (fichaFinanciera != null) {
            detalhesPropostaPF.setFichaFinanciera(new ArrayList<>());
            for (FichaFinanciera f : fichaFinanciera.getFichaFinanciera()) {
                if (f.getDataRenegociacao().compareTo(dataHoje) >= 0 &&
                        (f.getStatusPagamento().equals("RENEGOCIADO") ||
                                f.getStatusPagamento().equals("INCLUSAO DE TITULO"))) {
                    detalhesPropostaPF.getFichaFinanciera().add(f);
                }
            }
        } else {
            model.addAttribute("fichaFinanciera", new ArrayList<>());
        }


        return new ModelAndView("resumo_pf_proposta_detalhes", "detalhesPropostaPF", detalhesPropostaPF);
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

        String idx = temp[0];
        String t = idx;

        FichaFinancieraBoleto financieraBoleto = new FichaFinancieraBoleto();
        for (FichaFinanciera f : fichaFinanciera.getFichaFinanciera()) {
            f.getCompetencia().replaceAll("/", "");
            if (f.getCompetencia().contains(t)) {
                //Dados para download
                financieraBoleto.setCodigoDoAssociado(temp[1]);
                financieraBoleto.setDataVencimentoOriginal(f.getVencimentoOriginal());
                financieraBoleto.setNumeroParcela(f.getParcela());
                financieraBoleto.setTipoBoleto("PDF");
                financieraBoleto.setCodigoSistema("0");
                financieraBoleto.setRealizarRenegociacao("N");
            }
        }

        /*
        Date dataVencimento = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataVencimento);
        cal.add(Calendar.DATE, 5); // +5 DIAS
        dataVencimento = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00-03:00");
        financieraBoleto.setDataVencimento(sdf.format(dataVencimento).replace(" ", "T"));
        */

        byte[] file = propostaService.gerarBoleto(financieraBoleto);

        if (file == null) {
            model.addAttribute("error", "Não foi possível gerar boleto, por favor tente novamente.");
        } else {
            response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", "boleto.pdf");
            response.setHeader(headerKey, headerValue);
            response.getWriter().write(new String(file, "UTF-8"));
            response.getWriter().flush();
        }
    }

    @RequestMapping(value = "detalhesPropostaPME", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView detalhesPropostaPME(Model model, @ModelAttribute("cdEmpresa") String cdEmpresa) throws IOException, ParseException {

        Long numpag = 1l;
        Long tampag = 8l;

        paginacaoBeneficiario(model, cdEmpresa, numpag, tampag);

        return new ModelAndView("resumo_pme_proposta_detalhes", "detalhesPlanoPME", cdEmpresa);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView paginacaoBeneficiario(Model model,
                                              @RequestParam("cdEmpresa") String cdEmpresa,
                                              @RequestParam("numpag") Long numpag,
                                              @RequestParam("tampag") Long tampag) {

        EmpresaPropostaResponse detalhesPropostaPME = propostaService.detalhesPropostaPME(cdEmpresa);

        model.addAttribute("cnpj", detalhesPropostaPME.getCnpj());
        model.addAttribute("razaoSocial", detalhesPropostaPME.getRazaoSocial());
        model.addAttribute("incEstadual", detalhesPropostaPME.getIncEstadual());
        model.addAttribute("ramoAtividade", detalhesPropostaPME.getRamoAtividade());
        model.addAttribute("nomeFantasia", detalhesPropostaPME.getNomeFantasia());
        model.addAttribute("representanteLegal", detalhesPropostaPME.getRepresentanteLegal());
        model.addAttribute("contatoEmpresa", detalhesPropostaPME.isContatoEmpresa());
        model.addAttribute("telefone", detalhesPropostaPME.getTelefone());
        model.addAttribute("celular", detalhesPropostaPME.getCelular());
        model.addAttribute("email", detalhesPropostaPME.getEmail());
        model.addAttribute("vencimentoFatura", detalhesPropostaPME.getVencimentoFatura());
        model.addAttribute("cnae", detalhesPropostaPME.getCnae());
        model.addAttribute("dataVencimentoFatura", detalhesPropostaPME.getDataVencimentoFatura());
        model.addAttribute("enderecoEmpresa", detalhesPropostaPME.getEnderecoEmpresa());
        model.addAttribute("planos", detalhesPropostaPME.getPlanos());
        model.addAttribute("cnpjCorretora", detalhesPropostaPME.getCnpjCorretora());
        model.addAttribute("nomeCorretora", detalhesPropostaPME.getNomeCorretora());
        model.addAttribute("contactEmpresa", detalhesPropostaPME.getContactEmpresa());
        model.addAttribute("dataVigencia", detalhesPropostaPME.getDataVigencia());
        model.addAttribute("dataMovimentacao", detalhesPropostaPME.getDataMovimentacao());
        //model.addAttribute("dataAceite", detalhesPropostaPME.getDataAceite());
        model.addAttribute("cdStatusVenda", detalhesPropostaPME.getCdStatusVenda());
        model.addAttribute("cdEmpresa", detalhesPropostaPME.getCdEmpresa());
        model.addAttribute("empDcms", detalhesPropostaPME.getEmpDcms());
        model.addAttribute("cpfRepresentante", detalhesPropostaPME.getCpfRepresentante());

        List<BeneficiariosPropostaResponsePagination> beneficiarios = propostaService.detalhesBeneficiarioPropostaPME(detalhesPropostaPME.getCdEmpresa(), numpag, tampag);
        for (BeneficiariosPropostaResponsePagination p : beneficiarios) {
            model.addAttribute("tampags", p.getTamPagina());
            model.addAttribute("selectedPageSize", p.getTamPagina());
            model.addAttribute("numpag", p.getNumPagina());
            model.addAttribute("totalPages", p.getQtdPaginas());
            model.addAttribute("qtdRegistros", p.getQtdRegistros());
            model.addAttribute("titulares", p.getTitulares());
        }
        model.addAttribute("beneficiarios", beneficiarios);

        return new ModelAndView("resumo_pme_proposta_detalhes", "detalhesPlanoPME", beneficiarios);
    }


    @RequestMapping(value = "/downloadContratacao", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView downloadContratacao(Model model, HttpServletResponse response, @RequestParam("cdEmpresa") String cdEmpresa) throws IOException, ParseException {

        ResponseEntity<String> file = propostaService.gerarArquivoContratacao(Long.parseLong(cdEmpresa));

        if (file == null) {
            detalhesPropostaPME(model, cdEmpresa);
            model.addAttribute("error", "O arquivo não foi encontrado em nosso sistema.");
            return new ModelAndView("resumo_pme_proposta_detalhes", "arquivoContratacao", file);
        } else {
            String fileName = file.getHeaders().get("Content-Disposition").get(0).split(";")[1].split("=")[1];

            response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(headerKey, headerValue);
            response.getWriter().write(file.toString());
            response.getWriter().flush();
        }

        detalhesPropostaPME(model, cdEmpresa);
        return new ModelAndView("resumo_pme_proposta_detalhes", "arquivoContratacao", file);
    }

    /*
    @RequestMapping(value = "/downloadContratacao", method = RequestMethod.GET)
    @ResponseBody
    public void downloadContratacao(Model model, HttpServletResponse response, @RequestParam("cdEmpresa") String cdEmpresa) throws IOException {

        ArquivoContratacao file = propostaService.gerarArquivoContratacaoJson(Long.parseLong(cdEmpresa));

        if (file == null) {
            model.addAttribute("error", "Não foi possível gerar arquivo, porfavor tente novamente.");
        } else {
            byte[] decodedString = Base64.getDecoder().decode(file.getArquivoBase64());

            response.setContentType(String.valueOf(MediaType.APPLICATION_PDF));
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getNomeArquivo());
            response.setHeader(headerKey, headerValue);
            response.getWriter().write(new String(decodedString, "UTF-8"));
            response.getWriter().flush();
        }
    }
    */
}