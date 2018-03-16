package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Beneficiario;
import br.com.odontoprev.portalcorretor.model.Carrinho;
import br.com.odontoprev.portalcorretor.model.Dependente;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import br.com.odontoprev.portalcorretor.service.EnderecoService;
import br.com.odontoprev.portalcorretor.service.VendaPMEService;
import br.com.odontoprev.portalcorretor.service.dto.ConverterModelVendaPmeRequest;
import br.com.odontoprev.portalcorretor.service.dto.VendaPMERequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

import static br.com.odontoprev.portalcorretor.service.dto.Plano.Integral_DOC_LE;
import static br.com.odontoprev.portalcorretor.service.dto.Plano.Master_LALE;

@Controller
public class VendaPmeController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VendaPmeController.class);
    //private static final String URL_CEP = "https://api.odontoprev.com.br:8243/cep/1.1/por/cep/";

    //        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

    @Autowired
    private EnderecoService enderecoService;

    @RequestMapping(value = "venda/pme/Escolha_um_plano")
    public ModelAndView escolhaPlanoPme(HttpSession session) throws IOException {
        Carrinho carrinho = new Carrinho();
        session.setAttribute("carrinho", carrinho);
        return new ModelAndView("venda/pme/1_Escolha_um_plano", "carrinho", carrinho);
    }

    @RequestMapping(value = "venda/pme/Escolha_um_plano/addPlanoSelecionadoPme/{nomePlano}")
    public ModelAndView planoSelecionadoPme(@PathVariable("nomePlano") String nomePlano, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (nomePlano.equals("Master-LALE")) {
            carrinho.getPlanos().add(Master_LALE);

        } else if (nomePlano.equals("Integral-DOC-LALE")) {
            carrinho.getPlanos().add(Integral_DOC_LE);
        }
        return new ModelAndView("venda/pme/2_Plano_selecionado", "carrinho", carrinho);
    }

    @RequestMapping(value = "venda/pme/Escolha_um_plano/addPlanoSelecionadoPme")
    public ModelAndView planoSelecionadoPme(HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho.getPlanos().contains(Integral_DOC_LE)) {
            carrinho.getPlanos().add(Master_LALE);
        } else {
            carrinho.getPlanos().add(Integral_DOC_LE);
        }
        return new ModelAndView("venda/pme/2_Plano_selecionado", "carrinho", carrinho);
    }


    @RequestMapping(value = "venda/pme/Escolha_um_plano/deletePlanoSelecionadoPme")
    public ModelAndView removePlano(@PathVariable("nomePlano") String nomePlano, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");


        carrinho.getPlanos().remove(carrinho.getPlanos().size() - 1);

        return new ModelAndView("venda/pme/2_Plano_selecionado", "vendaPme", new VendaPme());
    }

    @RequestMapping(value = "venda/pme/Escolha_um_plano/AddBeneficiarioDependente")
    public ModelAndView addPlanoBeneficiarioDependente(Carrinho carrinhoForm, HttpSession session) {
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        carrinho.setVendaPme(carrinhoForm.getVendaPme());

        carrinho.setBeneficiarios(carrinho.getPlanos().stream().map(plano -> new Beneficiario(plano.getCdPlano())).collect(Collectors.toList()));
        return new ModelAndView("venda/pme/3_Add_beneficiario_dependente", "carrinho", carrinho);
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
//        fluxoVendaController.add("vendaPme", vendaPme);
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
