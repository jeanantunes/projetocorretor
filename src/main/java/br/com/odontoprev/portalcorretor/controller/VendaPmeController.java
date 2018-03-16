package br.com.odontoprev.portalcorretor.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.odontoprev.portalcorretor.model.Beneficiario;
import br.com.odontoprev.portalcorretor.model.Dependente;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import br.com.odontoprev.portalcorretor.service.EnderecoService;
import br.com.odontoprev.portalcorretor.service.VendaPMEService;
import br.com.odontoprev.portalcorretor.service.dto.ConverterModelVendaPmeRequest;
import br.com.odontoprev.portalcorretor.service.dto.Plano;
import br.com.odontoprev.portalcorretor.service.dto.VendaPMERequest;

@Controller
public class VendaPmeController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VendaPmeController.class);
    //private static final String URL_CEP = "https://api.odontoprev.com.br:8243/cep/1.1/por/cep/";

    @Autowired
    FluxoVendaController fluxoVendaController = new FluxoVendaController();
    
    @Autowired
    private EnderecoService enderecoService;


    // Inicio -> Metodos de Fluxo de Tela
    @RequestMapping(value = "escolhaUmPlanoPme")
    public ModelAndView escolhaPlanoPme(HttpSession session) throws IOException {
        fluxoVendaController.inicioFluxoVenda(session);
        enderecoService.obterEnderecoCorretora("03910020");
        return new ModelAndView("venda/pme/1_Escolha_um_plano");
    }

    @RequestMapping(value = "planoSelecionadoPme/{nomePlano}")
    public ModelAndView planoSelecionadoPme(@PathVariable("nomePlano") String nomePlano) {
        ModelAndView modelAndView = new ModelAndView("venda/pme/2_Plano_selecionado");

        Plano plano = new Plano();
        if (nomePlano.equals("Master-LALE")) {
            plano.setNome("Master-LALE");
            plano.setValor("119");
            plano.setCentavo("48");
            plano.setDesc("Modalidade Compulsoria");
        } else if (nomePlano.equals("Integral-DOC-LALE")) {
            plano.setNome("Integral-DOC-LALE");
            plano.setValor("37");
            plano.setCentavo("82");
            plano.setDesc("Modalidade Compulsoria");
        }
        VendaPme vendaPme = new VendaPme();
        //     fluxoVendaController.add("planoSelecionado",plano);

        modelAndView.addObject("vendaPme", vendaPme);
        modelAndView.addObject("planoSelecionado", plano);

        return modelAndView;
    }

    @RequestMapping(value = "AddBeneficiarioDependente")
    public ModelAndView addPlanoBeneficiarioDependente(VendaPme vendaPme) {
        ModelAndView modelAndView = new ModelAndView("venda/pme/3_Add_beneficiario_dependente");

        Beneficiario beneficiario = new Beneficiario();
        Dependente dependente = new Dependente();

        modelAndView.addObject("vendaPme", vendaPme);
        modelAndView.addObject("beneficiario", beneficiario);
        modelAndView.addObject("dependente", dependente);
        fluxoVendaController.add("beneficiario", beneficiario);
        fluxoVendaController.add("dependente", dependente);

        return modelAndView;
    }

    @RequestMapping(value = "confirmacaoProposta")
    public ModelAndView confirmacaoProposta(Dependente dependente) {
        VendaPMERequest vendaPMERequest = new VendaPMERequest();
        ConverterModelVendaPmeRequest converterModelVendaPmeRequest = new ConverterModelVendaPmeRequest();

        //vendaPMERequest = converterModelVendaPmeRequest.converter(vendaPme);
        //TODO Servico retornarndo URL Null dentro de VendaPMEService, Alterar parametro confirmacaoProposta
        VendaPMEService vendaPMEService = new VendaPMEService();



       vendaPMEService.Vender(vendaPMERequest);
        return new ModelAndView("venda/pme/4_confirmacao_proposta_pme");
    }
    // Fim -> Metodos de Fluxo de Tela


    @RequestMapping(value = "buscaCnpjPme")
    public ModelAndView buscaCnpjPme(@RequestParam("cnpj") String cnpj) {
        ModelAndView modelAndView = new ModelAndView();
        VendaPme vendaPme = new VendaPme();
        vendaPme = mockDados();
        vendaPme.setNomeFantasia("ALOOOU");
        fluxoVendaController.add("vendaPme", vendaPme);
        return new ModelAndView("venda/pme/2_Plano_selecionado", "vendaPme", vendaPme);
    }


   /* @RequestMapping(value = "buscaCEPPme/{cep}")
    private String buscaCEPPme(@PathVariable("cep") String cep) {
        String jsonInString = "";
        List<String> list = new ArrayList<>();

        try {
            URL url = new URL(URL_CEP + cep);
            ObjectMapper mapper = new ObjectMapper();
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<String>>() {
            });
            jsonInString = mapper.writeValueAsString(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
        return jsonInString;

    }*/


   /* public String obterToken() {
        HttpURLConnection con = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
            Request request = new Request.Builder()
                    .url("https://api.odontoprev.com.br:8243/token")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Basic Y3hHZXBoTzFkcERDd3U0VHlfRExWTWxXQ0R3YTp0WlJtSUN1eUJWajJZRVczRjdaNXdWM2E0YVlh")
                    .addHeader("Cache-Control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            Token token = (Token) mapper.readValue(response.body().string(), Token.class);
            return token.getAccess_token();
        } catch (Exception e) {
            return "";
        }

    }


    public HttpURLConnection obterConexao(URL url) {

        try {
            ApiManagerToken apiManager = ApiManagerTokenFactory.create(ApiManagerTokenEnum.WSO2, "PORTAL_CORRETOR_WEB");
            ApiToken apiToken = apiManager.generateToken();

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + obterToken());
            return con;
        } catch (CredentialsInvalidException | URLEndpointNotFound | ConnectionApiException | IOException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {

        }
    }

    private String readInputStreamToString(HttpURLConnection connection) {
        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        } catch (Exception e) {

            result = "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        return result;
    }*/


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
        beneficiario.setNomeDaMae("Maria Mae do Roberto");
        beneficiario.setSexo("M");
        beneficiario.setPlano("2");

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
