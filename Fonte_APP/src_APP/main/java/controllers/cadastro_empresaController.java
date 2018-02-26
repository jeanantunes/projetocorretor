package controllers;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import models.DataBase;
import models.Empresa;
import models.Plano;
import models.tabelaEmpresa;
import models.tablePlanos;

/**
 * Created by sleke on 24/02/2018.
 */

public class cadastro_empresaController {

    Context context;

    public cadastro_empresaController(Context ctx) {
        this.context = ctx;
    }

    @JavascriptInterface
    public Empresa EmpresaFromJSON(String jsonString)
    {
        //TODO EU INSERIR PRIMEIRO O ENDERECO E DEPOIS BUSCAR O ID DELE E RETORNAR PARA A CLASSE

        Log.d("MeuLog", "Inserir empresa");

        Gson gson = new Gson();

        Empresa empresa = gson.fromJson(jsonString, Empresa.class);

        Log.d("MeuLog", "Inserir esses dados:  "
                + empresa.getNomeFantasia() + " getNomeFantasia " +
                empresa.getCelular()+ " getCelular " +
                empresa.getCnpj()+ " getCnpj " +
                empresa.getIdEmpresa()+ " getIdEmpresa " +
                empresa.getRazaoSocial()+ " getRazaoSocial " +
                empresa.getRamoAtividade()+ " getRamoAtividade " +
                empresa.getEnderecoEmpresa().getEndereco() + " getEnderecoEmpresa.getEndereco "
        );

        boolean insertOK = inserirEmpresa(empresa);

        if (insertOK)
            return empresa;
        else
            return null;
    }

    @JavascriptInterface
    private boolean inserirEmpresa(Empresa empresa) {
        boolean insertOK = false;

        try{
            DataBase db = new DataBase(context);

            Log.d("MeuLog", "Inicio insert");

            ContentValues values = new ContentValues();

            values.put(tabelaEmpresa.COLUNA_CNPJ, empresa.getCnpj());
            values.put(tabelaEmpresa.COLUNA_RAZAOSOCIAL, empresa.getRazaoSocial());
            values.put(tabelaEmpresa.COLUNA_NOMEFANTASIA, empresa.getNomeFantasia());
            values.put(tabelaEmpresa.COLUNA_RAMOATIVIDADE, empresa.getRamoAtividade());
            values.put(tabelaEmpresa.COLUNA_INSCRICAOESTADUAL, empresa.getInscricaoEstadual());
            values.put(tabelaEmpresa.COLUNA_REPRESENTANTELEGAL, empresa.getRepresentanteLegal());
            values.put(tabelaEmpresa.COLUNA_CPF, empresa.getCpf());
            values.put(tabelaEmpresa.COLUNA_TELEFONE, empresa.getTelefone());
            values.put(tabelaEmpresa.COLUNA_CELULAR, empresa.getCelular());
            values.put(tabelaEmpresa.COLUNA_EMAIL, empresa.getEmail());
            values.put(tabelaEmpresa.COLUNA_REPRESENTANTELEGLCONTATOEMPRESA, empresa.getRepresentanteLegalContatoEmpresa());
            values.put(tabelaEmpresa.COLUNA_ENDERECOEMPRESA, empresa.getEnderecoEmpresa().getIdEnderecoEmpresa());
            values.put(tabelaEmpresa.COLUNA_ENDERECOCORRESPONDENTEMESMO, empresa.getEnderecoCorrespondenteMesmo());

            db.addInsert(tabelaEmpresa.TABELA_EMPRESAS, values);

            db.close();

            Log.d("MeuLog", "insert realizado com sucesso");

            insertOK = true;
        }catch (Exception e){
            insertOK = false;
            Log.d("MeuLog", "Erro: " + e.getMessage());
        }

        return insertOK;
    }


}
