package com.vendaodonto.vendasodontoprev.requests.models;

/**
 * Created by sleke on 25/02/2018.
 */

public class ForcaVendaModelPostRequest {
    private String email;

    private String departamento;

    private String ativo;

    private String dataNascimento;

    private String cpf;

    private String nome;

    private String cargo;

    private CorretoraModelRequest corretora;

    private String celular;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getDepartamento ()
    {
        return departamento;
    }

    public void setDepartamento (String departamento)
    {
        this.departamento = departamento;
    }

    public String getAtivo ()
    {
        return ativo;
    }

    public void setAtivo (String ativo)
    {
        this.ativo = ativo;
    }

    public String getDataNascimento ()
    {
        return dataNascimento;
    }

    public void setDataNascimento (String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf ()
    {
        return cpf;
    }

    public void setCpf (String cpf)
    {
        this.cpf = cpf;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getCargo ()
    {
        return cargo;
    }

    public void setCargo (String cargo)
    {
        this.cargo = cargo;
    }

    public CorretoraModelRequest getCorretora ()
    {
        return corretora;
    }

    public void setCorretora (CorretoraModelRequest corretora)
    {
        this.corretora = corretora;
    }

    public String getCelular ()
    {
        return celular;
    }

    public void setCelular (String celular)
    {
        this.celular = celular;
    }
}
