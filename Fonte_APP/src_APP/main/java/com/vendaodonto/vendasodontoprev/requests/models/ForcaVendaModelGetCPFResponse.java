package com.vendaodonto.vendasodontoprev.requests.models;

/**
 * Created by sleke on 25/02/2018.
 */

public class ForcaVendaModelGetCPFResponse {
    private String statusForcaVenda;

    private String email;

    private String departamento;

    private String ativo;

    private String cdForcaVenda;

    private String dataNascimento;

    private String cpf;

    private String nome;

    private String cargo;

    private CorretoraModelResponse corretora;

    private String celular;

    public String getStatusForcaVenda ()
    {
        return statusForcaVenda;
    }

    public void setStatusForcaVenda (String statusForcaVenda)
    {
        this.statusForcaVenda = statusForcaVenda;
    }

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

    public String getCdForcaVenda ()
    {
        return cdForcaVenda;
    }

    public void setCdForcaVenda (String cdForcaVenda)
    {
        this.cdForcaVenda = cdForcaVenda;
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

    public CorretoraModelResponse getCorretora ()
    {
        return corretora;
    }

    public void setCorretora (CorretoraModelResponse corretora)
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

    @Override
    public String toString()
    {
        return "ClassPojo [statusForcaVenda = "+statusForcaVenda+", email = "+email+", departamento = "+departamento+", ativo = "+ativo+", cdForcaVenda = "+cdForcaVenda+", dataNascimento = "+dataNascimento+", cpf = "+cpf+", nome = "+nome+", cargo = "+cargo+", corretora = "+corretora+", celular = "+celular+"]";
    }
}
