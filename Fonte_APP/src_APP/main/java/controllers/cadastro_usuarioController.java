package controllers;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.vendaodonto.vendasodontoprev.requests.ForcaVendaRequest;
import com.vendaodonto.vendasodontoprev.requests.models.CorretoraModelRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaBuscaCNPJModelResponse;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelGetCPFResponse;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelPostRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelPostResponse;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelResponse;
import com.vendaodonto.vendasodontoprev.sistemas.OdontoPrevApp;

import org.json.JSONException;
import org.json.JSONObject;

import models.Cliente;
import models.DataBase;
import utils.CustomWebView;
import utils.connection.WebClientCallback;
import utils.connection.WebClientResponse;


public class cadastro_usuarioController
{
    Context context;
    private boolean cpfValido = false;
    private boolean cnpjValido = false;
    private boolean controllRequest = false;
    private ForcaVendaModelGetCPFResponse forcaVendaModelGetCPFResponse;
    private ForcaVendaBuscaCNPJModelResponse forcaVendaBuscaCNPJModelResponse;

    private boolean cadastradoSucesso = false;

    public cadastro_usuarioController(Context ctx) {
        this.context = ctx;
    }


    @JavascriptInterface
    public void imprimirCliente()
    {
        Log.i("MeuLog", "teste");
    }

    @JavascriptInterface
    public int calculateSum(int numA, int numB)
    {
        //Intent pagina2 = new Intent(MainActivity.this, Main2Activity.class);

        //pagina2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //startActivity(pagina2);

        Gson gson = new Gson();

        Log.i("MeuLog", "teste");

        return numA - numB;
    }

    @JavascriptInterface
    public Cliente ClienteFromJSON(String jsonString)
    {
        Log.d("MeuLog", "Fogfjghi ");

        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonString, Cliente.class);

        Log.d("MeuLog", "Foi " + cliente.getNome() + " " + cliente.getCelular() + " " + cliente.getEmail() + " " + cliente.getCpf() + " " + cliente.getSenha());

        inserirUsuario(cliente);
        return cliente;
    }

    @JavascriptInterface
    private void inserirUsuario(Cliente cliente)
    {
        Log.d("MeuLog", "Foi " + cliente.getNome() + " " + cliente.getCelular() + " " + cliente.getEmail() + " " + cliente.getCpf());

        Log.d("MeuLog", "Foi");

        DataBase db = new DataBase(context);

        Log.d("MeuLog", "Foi1");

        db.addCliente(cliente);

        db.close();

        //Cad.inserir(cliente);

        //banco.incluirRegistro( cliente );
        Log.d("MeuLog", "Foi2");

        //dadosOpenHelper.close();
    }


    private void buscarCliente()
    {
        //Log.d("MeuLog", "" + Cad.buscar(0));
    }

    @JavascriptInterface
    public void imprimirLog()
    {
        Log.d("MeuLog", "Retornou");

    }

    @JavascriptInterface
    public void imprimirLogParam(String s)
    {
        Log.d("MeuLog", s);

    }

    @JavascriptInterface
    public boolean BuscaCNPJFromJSON(String jsonString)
    {
        //controla o request de tempo de chamada
        controllRequest = false;

        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonString, Cliente.class);

        ForcaVendaRequest forcaVendaRequest = new ForcaVendaRequest();

        forcaVendaRequest.requestGetBuscaCNPJ(context, new WebClientCallback() {

            @Override
            public void onSuccess(WebClientResponse response) {
                Gson gson = new Gson();

                forcaVendaBuscaCNPJModelResponse = gson.fromJson(response.getResult().toString(),ForcaVendaBuscaCNPJModelResponse.class);

                Log.d("MeuLog","Token :" + forcaVendaBuscaCNPJModelResponse.getId());

                if(forcaVendaBuscaCNPJModelResponse.getId().equals("0")){
                    cnpjValido = false;
                } else {
                    cnpjValido = true;
                }

                controllRequest = true;

                CustomWebView webView = OdontoPrevApp.getCustomWebView();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("validaCnpj();", null);
                } else {
                    webView.loadUrl("javascript:validaCnpj();");
                }
            }

            @Override
            public void onFailure(WebClientResponse response) {
                Log.d("MeuLog", "ERRO :"  + response.getMensagem());
                cnpjValido = false;
                forcaVendaBuscaCNPJModelResponse = null;

                controllRequest = true;

                CustomWebView webView = OdontoPrevApp.getCustomWebView();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("validarCpf();", null);
                } else {
                    webView.loadUrl("javascript:validarCpf();");
                }
            }
        },forcaVendaModelGetCPFResponse.getCdForcaVenda(),cliente.getCpf());

        return controllRequest;
    }

    @JavascriptInterface
    public boolean isCNPJValido(){
        return cnpjValido;
    }



    @JavascriptInterface
    public boolean BuscaForcaVendaFromJSON(String jsonString)
    {
        //controla o request de tempo de chamada
        controllRequest = false;

        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonString, Cliente.class);

        ForcaVendaRequest forcaVendaRequest = new ForcaVendaRequest();

        final ForcaVendaModelRequest forcaVendaModelRequest = new ForcaVendaModelRequest();
        forcaVendaModelRequest.setCpf(cliente.getCpf().replace(".","").replace("-","").replace(" ",""));

        forcaVendaRequest.requestGetBuscaCPF(context, new WebClientCallback() {

            @Override
            public void onSuccess(WebClientResponse response) {
                Gson gson = new Gson();

                forcaVendaModelGetCPFResponse = gson.fromJson(response.getResult().toString(),ForcaVendaModelGetCPFResponse.class);

                Log.d("MeuLog","Token :" + forcaVendaModelGetCPFResponse.getCargo());

                if(forcaVendaModelGetCPFResponse!=null &&
                        forcaVendaModelGetCPFResponse.getCelular()!=null &&
                        !forcaVendaModelGetCPFResponse.getCelular().equals("") &&

                        forcaVendaModelGetCPFResponse.getCpf()!=null &&
                        !forcaVendaModelGetCPFResponse.getCpf().equals("") &&

                        forcaVendaModelGetCPFResponse.getEmail()!=null &&
                        !forcaVendaModelGetCPFResponse.getEmail().equals("") &&

                        forcaVendaModelGetCPFResponse.getCdForcaVenda()!=null &&
                        !forcaVendaModelGetCPFResponse.getCdForcaVenda().equals("") &&

                        forcaVendaModelGetCPFResponse.getNome()!=null &&
                        !forcaVendaModelGetCPFResponse.getNome().equals("")

                        )
                    cpfValido = true;
                else
                    cpfValido = false;

                controllRequest = true;

                CustomWebView webView = OdontoPrevApp.getCustomWebView();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("validarCpf();", null);
                } else {
                    webView.loadUrl("javascript:validarCpf();");
                }
            }

            @Override
            public void onFailure(WebClientResponse response) {
                Log.d("MeuLog", "ERRO :"  + response.getMensagem());
                cpfValido = false;
                forcaVendaModelGetCPFResponse = null;

                controllRequest = true;

                CustomWebView webView = OdontoPrevApp.getCustomWebView();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("validarCpf();", null);
                } else {
                    webView.loadUrl("javascript:validarCpf();");
                }
            }
        },forcaVendaModelRequest);

        return controllRequest;
    }

    @JavascriptInterface
    public boolean isCPFValido(){
        return cpfValido;
    }

    @JavascriptInterface
    public String getDadosUsuario(){
        Gson gson = new Gson();
        return gson.toJson(forcaVendaModelGetCPFResponse);
    }

    @JavascriptInterface
    public boolean SalvaDadosForcaVendaFromJson(String jsonString){
        //controla o request de tempo de chamada
        controllRequest = false;

        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonString, Cliente.class);

        CorretoraModelRequest corretoraModelRequest = new CorretoraModelRequest();
        corretoraModelRequest.setCdCorretora(forcaVendaModelGetCPFResponse.getCorretora().getCdCorretora());

        ForcaVendaRequest forcaVendaRequest = new ForcaVendaRequest();

        ForcaVendaModelPostRequest forcaVendaModelRequest = new ForcaVendaModelPostRequest();
        forcaVendaModelRequest.setNome(cliente.getNome());
        forcaVendaModelRequest.setCpf(cliente.getCpf());
        forcaVendaModelRequest.setCelular(cliente.getCelular());
        forcaVendaModelRequest.setEmail(cliente.getEmail());
        forcaVendaModelRequest.setCorretora(corretoraModelRequest);
        forcaVendaModelRequest.setAtivo(forcaVendaModelGetCPFResponse.getAtivo());
        forcaVendaModelRequest.setDepartamento(forcaVendaModelGetCPFResponse.getDepartamento());
        forcaVendaModelRequest.setCargo(forcaVendaModelGetCPFResponse.getCargo());
        forcaVendaModelRequest.setDataNascimento(forcaVendaModelGetCPFResponse.getDataNascimento());


        forcaVendaRequest.requestPostForcaVenda(context, new WebClientCallback() {
            @Override
            public void onSuccess(WebClientResponse response) {
                //controla o request de tempo de chamada
                controllRequest = false;

                Gson gson = new Gson();
                ForcaVendaModelPostResponse forcaVendaModelPostResponse =  gson.fromJson(response.getResult().toString(), ForcaVendaModelPostResponse.class);

                if(forcaVendaModelPostResponse.getId().equals("0")){
                    cadastradoSucesso = false;
                } else {
                    cadastradoSucesso = true;
                }

                controllRequest = true;

                CustomWebView webView = OdontoPrevApp.getCustomWebView();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript("cadastrarRetorno();", null);
                } else {
                    webView.loadUrl("javascript:cadastrarRetorno();");
                }

            }

            @Override
            public void onFailure(WebClientResponse response) {
                Log.d("MeuLog", "ERRO :"  + response.getMensagem());
                cadastradoSucesso = false;
                controllRequest = true;
            }
        },forcaVendaModelRequest);

        return controllRequest;
    }

    @JavascriptInterface
    public boolean isForcaVendaSucesso(){
        return cadastradoSucesso;
    }

}
