package controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;
import java.util.List;

import models.DataBase;
import models.EnderecoEmpresa;
import models.tabelaEnderecoEmpresa;
import models.tablePlanos;

/**
 * Created by sleke on 24/02/2018.
 */

public class cadastro_enderecoEmpresaController {
    Context context;

    public cadastro_enderecoEmpresaController(Context ctx) {
        this.context = ctx;
    }

    @JavascriptInterface
    public boolean inserirEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
        boolean insertOK = false;

        try{
            DataBase db = new DataBase(context);

            Log.d("MeuLog", "Inicio insert");

            ContentValues values = new ContentValues();

            values.put(tabelaEnderecoEmpresa.COLUNA_BAIRRO, enderecoEmpresa.getBairro());
            values.put(tabelaEnderecoEmpresa.COLUNA_CIDADE, enderecoEmpresa.getCidade());
            values.put(tabelaEnderecoEmpresa.COLUNA_COMPLEMENTO, enderecoEmpresa.getComplemento());
            values.put(tabelaEnderecoEmpresa.COLUNA_ENDERECO, enderecoEmpresa.getEndereco());
            values.put(tabelaEnderecoEmpresa.COLUNA_ESTADO, enderecoEmpresa.getEstado());
//            values.put(tabelaEnderecoEmpresa.COLUNA_IDENDERECOEMPRESA, enderecoEmpresa.getIdEnderecoEmpresa());
            values.put(tabelaEnderecoEmpresa.COLUNA_NUMERO, enderecoEmpresa.getNumero());
            values.put(tabelaEnderecoEmpresa.COLUNA_CEP, enderecoEmpresa.getCep());

            db.addInsert(tabelaEnderecoEmpresa.TABELA_ENDERECOEMPRESA, values);

            db.close();

            Log.d("MeuLog", "insert realizado com sucesso");

            insertOK = true;
        }catch (Exception e){
            insertOK = false;
            Log.d("MeuLog", "Erro: " + e.getMessage());
        }

        return insertOK;
    }

    @JavascriptInterface
    public EnderecoEmpresa BuscarEnderecoEmpresaFromJSON(String tabela, String id){

        DataBase dataBase = new DataBase(context);

        EnderecoEmpresa enderecoEmpresa = new EnderecoEmpresa();

        Log.i("MeuLog", "Executar Buscar "+ tabela + " id = "+ id);

        Cursor cursor = dataBase.buscarTable(tabelaEnderecoEmpresa.TABELA_ENDERECOEMPRESA, tablePlanos.COLUNA_IDPLANOS, id);

        if(cursor!=null) {
            cursor.moveToFirst();

            enderecoEmpresa.setBairro(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_BAIRRO)));
            enderecoEmpresa.setCep(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_CEP)));
            enderecoEmpresa.setCidade(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_CIDADE)));
            enderecoEmpresa.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_COMPLEMENTO)));
            enderecoEmpresa.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_ENDERECO)));
            enderecoEmpresa.setEstado(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_ESTADO)));
            enderecoEmpresa.setIdEnderecoEmpresa(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_IDENDERECOEMPRESA)));
            enderecoEmpresa.setNumero(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_NUMERO)));

        } else {
            enderecoEmpresa = null;
        }

        return enderecoEmpresa;
    }

    @JavascriptInterface
    public List<EnderecoEmpresa> BuscarTodosEnderecoEmpresaFromJSON(String tabela){

        DataBase dataBase = new DataBase(context);

        List<EnderecoEmpresa> enderecoEmpresaList = new ArrayList<>();

        Log.i("MeuLog", "Executar Buscar todos da "+ tabela );

        Cursor cursor = dataBase.buscarTable(tabelaEnderecoEmpresa.TABELA_ENDERECOEMPRESA, tablePlanos.COLUNA_IDPLANOS, null);

        if(cursor!=null) {
            while (cursor.moveToNext()){
                EnderecoEmpresa enderecoEmpresa = new EnderecoEmpresa();

                enderecoEmpresa.setBairro(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_BAIRRO)));
                enderecoEmpresa.setCep(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_CEP)));
                enderecoEmpresa.setCidade(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_CIDADE)));
                enderecoEmpresa.setComplemento(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_COMPLEMENTO)));
                enderecoEmpresa.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_ENDERECO)));
                enderecoEmpresa.setEstado(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_ESTADO)));
                enderecoEmpresa.setIdEnderecoEmpresa(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_IDENDERECOEMPRESA)));
                enderecoEmpresa.setNumero(cursor.getString(cursor.getColumnIndexOrThrow(tabelaEnderecoEmpresa.COLUNA_NUMERO)));

                enderecoEmpresaList.add(enderecoEmpresa);
            }
        } else {
            enderecoEmpresaList = null;
        }

        return enderecoEmpresaList;
    }


}
