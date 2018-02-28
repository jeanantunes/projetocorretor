package com.vendaodonto.vendasodontoprev;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vendaodonto.vendasodontoprev.requests.ForcaVendaRequest;
import com.vendaodonto.vendasodontoprev.requests.PlanosRequest;
import com.vendaodonto.vendasodontoprev.requests.TokenRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelRequest;
import com.vendaodonto.vendasodontoprev.requests.models.ForcaVendaModelResponse;
import com.vendaodonto.vendasodontoprev.sistemas.OdontoPrevApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import controllers.cadastro_enderecoEmpresaController;
import controllers.cadastro_planoController;
import models.Cliente;
import models.DataBase;
import models.EnderecoEmpresa;
import models.ForcaVenda;
import models.Plano;
import models.Token;
import models.tabelaEnderecoEmpresa;
import models.tableCorretora;
import models.tableEndereco;
import models.tableForcaVendas;
import models.tableLogin;
import models.tablePlanos;
import models.tableStatusForcaVendas;
import utils.CustomWebView;
import utils.CustomWebViewClient;
import utils.WebClient;
import utils.connection.WebClientCallback;
import utils.connection.WebClientResponse;
import utils.connection.WebClientSecurity;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    //CustomWebView myWebView;

    DataBase db;

    public static Context ctx;

    private final int GALERIA_IMAGENS = 1;


    public Context getCtx() {
        return ctx;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CustomWebView myWebView;

        db = new DataBase(this);

        super.onCreate(savedInstanceState);

        ctx = this;

        setContentView(R.layout.activity_main);
        String urlAssets = "file:///android_asset/";

        myWebView = (CustomWebView) this.findViewById(R.id.webView);
        myWebView.setWebViewClient(new CustomWebViewClient(ctx));

        getSupportActionBar().hide();

        //setCtx(ctx);
//        try {
//
//            myWebView.getSettings().setJavaScriptEnabled(true);
//            myWebView.loadUrl(myurl);
//
//            //New//Folder//AIDL Folder
//            //New//File
//        } catch (Exception e) {
//            Log.e("MYAPP", "======================");
//            Log.e("MYAPP", "exception", e);
//        }

        //getContact(1);

        int qt = 0;
        //tableLogin tb = new tableLogin(this);
        //tb.insertTeste();
        try {

            Log.e("MeuLog", "======================");
            AssetManager assetManager = this.getAssets();
            InputStream stream = assetManager.open("index.html");
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            StringBuilder total = new StringBuilder();
            String line;

            while ((line = r.readLine()) != null) {
                total.append(line).append("\n");
                qt ++;
            }

            //Toast.makeText(this.getBaseContext(), String.valueOf(qt), Toast.LENGTH_SHORT).show();
            Toast.makeText(this.getBaseContext(), "" + qt, Toast.LENGTH_SHORT).show();

            //myWebView.loadUrl("https://imobottst5.mybluemix.net");

            Handler handler = new Handler();

            handler.post(new Runnable() {
                @Override
                public void run() {

                    TokenRequest tokenRequest = new TokenRequest();

                    tokenRequest.requestToken(ctx, new WebClientCallback() {
                        @Override
                        public void onSuccess(WebClientResponse response) {
                            Gson gson = new Gson();

                            Token token = gson.fromJson(response.getResult().toString(),Token.class);

                            WebClientSecurity.getInstance().setToken(token.getAccess_token());
//
//                            ForcaVendaRequest forcaVendaRequest = new ForcaVendaRequest();
//
//                            ForcaVendaModelRequest forcaVendaModelRequest = new ForcaVendaModelRequest();
//                            forcaVendaModelRequest.setCpf("07385355080");
//
//                            forcaVendaRequest.requestGetBuscaForcaVenda(ctx, new WebClientCallback() {
//                                @Override
//                                public void onSuccess(WebClientResponse response) {
//                                    Gson gson = new Gson();
//
//                                    ForcaVendaModelResponse forcaVendaModelResponse = gson.fromJson(response.getResult().toString(),ForcaVendaModelResponse.class);
//
//                                    Log.d("MeuLog","Token :" + forcaVendaModelResponse.getCargo());
//                                }
//
//                                @Override
//                                public void onFailure(WebClientResponse response) {
//                                    Log.d("MeuLog", "ERRO :"  + response.getMensagem());
//                                }
//                            },forcaVendaModelRequest);
                            Log.d("MeuLog","Token :" + token.getAccess_token());
                        }

                        @Override
                        public void onFailure(WebClientResponse response) {
                            Log.d("MeuLog", "ERRO :"  + response.getMensagem());
                        }
                    });
                }
            });

//            new Thread(runnable).start();

            ForcaVenda forcaLogin = buscar(1);

            tableLogin tb = new tableLogin(this);

            //Log.d("MeuLog", "Cadastro id: " + forcaLogin.getLogado());

            //if (forcaLogin == null)
            //{
            //    Log.d("MeuLog","forcaLogin == null");
            //    tb.insertTeste();
            //}

            //forcaLogin = buscar(1);

            if(forcaLogin != null)
            {
                myWebView.loadUrl("file:///android_asset/anteriorLogado.html");
            }
            else
            {
                Log.d("MeuLog", "Nâo existe cadastro salvo");
                myWebView.loadUrl("file:///android_asset/index.html");
           }


            //myWebView.loadDataWithBaseURL("file:///android_asset/", total.toString(), "text/html", "UTF-8", null);

            Log.d("MeuLog", "Classe instanciada");

            Toast.makeText(this.getBaseContext(), "" + qt, Toast.LENGTH_LONG).show();

            //selectTableLogin();



        } catch (Exception xxx) {
            Log.e("MeuLog", "Load assets ", xxx);
        }



        // TESTE CRUD

        //db.addCliente(new Cliente("Nome", "CPF", "58455", "DSDA", "SDAASDAS"));

        //Toast.makeText(this.getBaseContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();

        ///////////////////////////////////////////////

        OdontoPrevApp.setCustomWebView(myWebView);
    }

    public ForcaVenda buscar(int codigo) {

        ForcaVenda forca = new ForcaVenda();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * ");
        sql.append("FROM Login ");
        sql.append("WHERE IdLogin = " + codigo);

        SQLiteDatabase dbs = db.getReadableDatabase();

        Cursor resultado = dbs.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            forca.setCargo(resultado.getString( resultado.getColumnIndexOrThrow("cargo")));
            forca.setCpf(resultado.getString( resultado.getColumnIndexOrThrow("cpf")));
            forca.setLogado(resultado.getString( resultado.getColumnIndexOrThrow("logado")));
            forca.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("email")));
            forca.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
            forca.setNomeEmpresa(resultado.getString(resultado.getColumnIndexOrThrow("nomeEmpresa")));


            Log.i("MeuLog", "Executou BuscarEmpresa");
            return forca;
        }

        return null;
    }

    @Override
    public void onBackPressed() {
        CustomWebView myWebView = OdontoPrevApp.getCustomWebView();

        if (myWebView.canGoBack())
        {
            String url = myWebView.getOriginalUrl().toString();
            Log.d("MeuLog", url);

            //this.myWebView.goBack();

            if (url.equals("file:///android_asset/index.html"))
            {
//
                Log.d("MeuLog", "Close WebView");
                this.finish();
//
            } else myWebView.goBack();

        } else {

            Log.d("MeuLog", "Close Activity");
            this.finish();
        }

        OdontoPrevApp.setCustomWebView(myWebView);
    }

    public void abrirgaleria()
    {
        Log.d("MeuLog", "Executou o metodo abrirgaleria");
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);//
            startActivityForResult(intent, GALERIA_IMAGENS);

        }catch (Exception e)
        {
            Log.d("MeuLog", "Erro: " + e.toString());
        }
    }

    public void abrirActivityGaleria()
    {
        Intent intent = new Intent(this, GaleriaActivity.class);
        startActivity(intent);
    }
}
