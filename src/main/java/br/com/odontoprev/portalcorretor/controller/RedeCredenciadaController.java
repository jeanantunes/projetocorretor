package br.com.odontoprev.portalcorretor.controller;


import br.com.odontoprev.api.manager.client.token.ApiManagerToken;
import br.com.odontoprev.api.manager.client.token.ApiManagerTokenFactory;
import br.com.odontoprev.api.manager.client.token.ApiToken;
import br.com.odontoprev.api.manager.client.token.enumerator.ApiManagerTokenEnum;
import br.com.odontoprev.api.manager.client.token.exception.ConnectionApiException;
import br.com.odontoprev.api.manager.client.token.exception.CredentialsInvalidException;
import br.com.odontoprev.api.manager.client.token.exception.URLEndpointNotFound;
import br.com.odontoprev.portalcorretor.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RedeCredenciadaController {

    private static final String URL_ESTADO = "https://api.odontoprev.com.br:8243/cep/1.1/estados";
    private static final String URL_CIDADE = "https://api.odontoprev.com.br:8243/cep/1.1/cidades/uf?uf=";
    private static final String URL_BAIRRO = "https://api.odontoprev.com.br:8243/cep/1.1/bairros?uf=";
    private static final String URL_ESPECIALIDADE = "https://api.odontoprev.com.br:8243/redecredenciada/1.0/especialidades";
    private static final String URL_DENTISTAS = "https://api.odontoprev.com.br:8243/dcms/redecredenciada/1.0/dentistas?codigoBeneficiario=";

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedeCredenciadaController.class);
    List<Estado> listaEstados = new ArrayList<>();
    List<Cidade> listaCidades = new ArrayList<>();
    List<Bairro> listaBairros = new ArrayList<>();
    List<Especialidade> listaEspecialidades = new ArrayList<>();
    ModelAndView modelAndView = new ModelAndView("rede-credenciada");


    @RequestMapping(value = "rede-credenciada")
    public ModelAndView index() {

        listaEstados = buscaEstados();
        listaEspecialidades = buscaEspecialidades("");
        modelAndView.addObject("estado", listaEstados);
        modelAndView.addObject("especialidade", listaEspecialidades);

        return modelAndView;
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @RequestMapping(value = "rede-credenciada/especialidade/{codEspecialidade}")
    public List<Especialidade >buscaEspecialidades(@PathVariable("codEspecialidade") String codEspecialidade) {
        List<Especialidade> list = new ArrayList<>();
        if(!codEspecialidade.isEmpty()){
            modelAndView.addObject("codEspecialidadeSelecionado", codEspecialidade);
        }
        try {

            URL url = new URL(URL_ESPECIALIDADE);

            ObjectMapper mapper = new ObjectMapper();
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<Especialidade>>() {
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }


    @Consumes({"application/json"})
    @Produces({"application/json"})
    public List<Estado> buscaEstados() {
        List<Estado> list = new ArrayList<>();
        try {

            URL url = new URL(URL_ESTADO);

            ObjectMapper mapper = new ObjectMapper();
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<Estado>>() {
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @RequestMapping(value = "rede-credenciada/{uf}")
    public @ResponseBody String buscaCidades(@PathVariable("uf") String uf) {
        List<Cidade> list = new ArrayList<>();
        String jsonInString = "";
        try {

            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(URL_CIDADE + uf);
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<Cidade>>() {
            });

            jsonInString = mapper.writeValueAsString(list);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        listaCidades = list;
        modelAndView.addObject("estadoSelecionado", uf);
        modelAndView.addObject("cidade", listaCidades);

        return  jsonInString;
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @RequestMapping(value = "rede-credenciada/dentistas/{uf}/{codigoEspecialidade}/{codigoBairro}")
    public @ResponseBody String buscaDentistas(@PathVariable("uf") String uf, @PathVariable("codigoEspecialidade")
            String codigoEspecialidade,@PathVariable("codigoBairro") String codigoBairro) {

        String jsonInString = "";

        List<Bairro> list = new ArrayList<>();
        try {
            URL url = new URL(URL_DENTISTAS+"&codigoBeneficiario=241008130&siglaUf="+uf+
                    "&codigoEspecialidade="+codigoEspecialidade+"&privian=FALSE&codigoMarca=1&codigoBairro="+codigoBairro);
            ObjectMapper mapper = new ObjectMapper();
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<Bairro>>() {
            });
            jsonInString = mapper.writeValueAsString(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        listaBairros = list;
        return jsonInString;
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @RequestMapping(value = "rede-credenciada/bairros/{codigoCidade}")
    public @ResponseBody String buscaBairros(@PathVariable("codigoCidade") String codigoCidade) {
        String jsonInString = "";
        List<Bairro> list = new ArrayList<>();
        try {
            URL url = new URL(URL_BAIRRO + modelAndView.getModel().get("estadoSelecionado") + "&codigoCidade=" + codigoCidade + "&codigoBeneficiario=241008130");
            ObjectMapper mapper = new ObjectMapper();
            String json = readInputStreamToString(obterConexao(url));
            list = mapper.readValue(json, new TypeReference<List<Bairro>>() {
            });
            jsonInString = mapper.writeValueAsString(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        listaBairros = list;
        modelAndView.addObject("cidadeSelecionada", codigoCidade);
        modelAndView.addObject("bairro", listaBairros);
        return jsonInString;
    }

    public String obterToken() {
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
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public List<Bairro> getListaBairros() {
        return listaBairros;
    }

    public void setListaBairros(List<Bairro> listaBairros) {
        this.listaBairros = listaBairros;
    }

    public List<Especialidade> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }
}


