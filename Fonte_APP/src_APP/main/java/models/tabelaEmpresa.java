package models;

import android.content.Context;

/**
 * Created by sleke on 24/02/2018.
 */

public class tabelaEmpresa {

    Context context;
    DataBase db;

    public static final String TABELA_EMPRESAS = "Empresas";
    public static final String COLUNA_IDEMPRESA = "IdEmpresa";
    public static final String COLUNA_CNPJ = "cnpj";
    public static final String COLUNA_RAZAOSOCIAL = "razaoSocial";
    public static final String COLUNA_NOMEFANTASIA = "nomeFantasia";
    public static final String COLUNA_RAMOATIVIDADE = "ramoAtividade";
    public static final String COLUNA_INSCRICAOESTADUAL = "inscricaoEstadual";
    public static final String COLUNA_REPRESENTANTELEGAL = "representanteLegal";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_CELULAR = "celular";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_REPRESENTANTELEGLCONTATOEMPRESA = "representanteLeglContatoEmpresa";
    public static final String COLUNA_ENDERECOEMPRESA = "endereco";
    public static final String COLUNA_ENDERECOCORRESPONDENTEMESMO = "enderecoCorrespondenteMesmo";

    public tabelaEmpresa(Context ctx) {
        this.context = ctx;
        db = new DataBase(context);
    }

    public static String getTableEmpresas()
    {
        String table = "CREATE TABLE IF NOT EXISTS ["+TABELA_EMPRESAS+"] " +
                "( [IdEmpresas] integer NOT NULL," +
                " [cnpj] varchar(50) COLLATE NOCASE, " +
                " [razaoSocial] varchar(100) COLLATE NOCASE, " +
                " [nomeFantasia] varchar(100) COLLATE NOCASE, " +
                " [ramoAtividade] varchar(200) COLLATE NOCASE, " +
                " [inscricaoEstadual] varchar(50) COLLATE NOCASE, " +
                " [representanteLegal] varchar(100) COLLATE NOCASE, " +
                " [cpf] varchar(50) COLLATE NOCASE, " +
                " [telefone] varchar(20) COLLATE NOCASE, " +
                " [celular] varchar(20) COLLATE NOCASE, " +
                " [email] varchar(50) COLLATE NOCASE, " +
                " [representanteLegalContatoEmpresa] varchar(10) COLLATE NOCASE, " +
                " [enderecoCorrespondenteMesmo] varchar(10) COLLATE NOCASE, " +
                " [idEnderecoEmpresa] INTEGER, " +

                "PRIMARY KEY (["+TABELA_EMPRESAS+"])," +
                " CONSTRAINT FK_enderecoEmpresa FOREIGN KEY(idEnderecoEmpresa) REFERENCES EnderecoEmpresa)";

        return table;
    }
}
