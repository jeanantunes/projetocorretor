package models;

import android.content.Context;

/**
 * Created by sleke on 24/02/2018.
 */

public class tabelaEnderecoEmpresa {
    Context context;
    DataBase db;


    public static final String TABELA_ENDERECOEMPRESA = "EnderecoEmpresa";
    public static final String COLUNA_IDENDERECOEMPRESA = "idEnderecoEmpresa";
    public static final String COLUNA_CEP = "cep";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_COMPLEMENTO = "complemento";
    public static final String COLUNA_BAIRRO = "bairro";
    public static final String COLUNA_CIDADE = "cidade";
    public static final String COLUNA_ESTADO = "estado";

    public tabelaEnderecoEmpresa(Context ctx) {
        this.context = ctx;
        db = new DataBase(context);
    }

    public static String getTableEnderecoEmpresa()
    {
        String tableEndereco = " CREATE TABLE IF NOT EXISTS [EnderecoEmpresa] " +
                "( [idEnderecoEmpresa] integer NOT NULL, " +
                "[endereco] varchar(50) NOT NULL COLLATE NOCASE, " +
                "[numero] varchar(50) NOT NULL COLLATE NOCASE, " +
                "[cep] varchar(15) NOT NULL COLLATE NOCASE, " +
                "[cidade] varchar(30) NOT NULL COLLATE NOCASE, " +
                "[complemento] varchar(100) COLLATE NOCASE, " +
                "[bairro] varchar(50) NOT NULL COLLATE NOCASE, " +
                "[estado] varchar(2) NOT NULL COLLATE NOCASE, " +
                "PRIMARY KEY ([idEnderecoEmpresa]))";


        return tableEndereco;
    }

}
