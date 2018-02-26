package com.vendaodonto.vendasodontoprev.requests.models;

/**
 * Created by sleke on 25/02/2018.
 */

public class CorretoraModelRequest {
    private String statusCnpj;

    private String enderecoCorretora;

    private String cnae;

    private String cdCorretora;

    private String representantes;

    private String conta;

    private String dataAbertura;

    private String razaoSocial;

    private String cnpj;

    private String simplesNacional;

    private String email;

    private String telefone;

    private String login;

    private String celular;

    public String getStatusCnpj ()
    {
        return statusCnpj;
    }

    public void setStatusCnpj (String statusCnpj)
    {
        this.statusCnpj = statusCnpj;
    }

    public String getEnderecoCorretora ()
    {
        return enderecoCorretora;
    }

    public void setEnderecoCorretora (String enderecoCorretora)
    {
        this.enderecoCorretora = enderecoCorretora;
    }

    public String getCnae ()
    {
        return cnae;
    }

    public void setCnae (String cnae)
    {
        this.cnae = cnae;
    }

    public String getCdCorretora ()
    {
        return cdCorretora;
    }

    public void setCdCorretora (String cdCorretora)
    {
        this.cdCorretora = cdCorretora;
    }

    public String getRepresentantes ()
    {
        return representantes;
    }

    public void setRepresentantes (String representantes)
    {
        this.representantes = representantes;
    }

    public String getConta ()
    {
        return conta;
    }

    public void setConta (String conta)
    {
        this.conta = conta;
    }

    public String getDataAbertura ()
    {
        return dataAbertura;
    }

    public void setDataAbertura (String dataAbertura)
    {
        this.dataAbertura = dataAbertura;
    }

    public String getRazaoSocial ()
    {
        return razaoSocial;
    }

    public void setRazaoSocial (String razaoSocial)
    {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj ()
    {
        return cnpj;
    }

    public void setCnpj (String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getSimplesNacional ()
    {
        return simplesNacional;
    }

    public void setSimplesNacional (String simplesNacional)
    {
        this.simplesNacional = simplesNacional;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getTelefone ()
    {
        return telefone;
    }

    public void setTelefone (String telefone)
    {
        this.telefone = telefone;
    }

    public String getLogin ()
    {
        return login;
    }

    public void setLogin (String login)
    {
        this.login = login;
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
        return "ClassPojo [statusCnpj = "+statusCnpj+", enderecoCorretora = "+enderecoCorretora+", cnae = "+cnae+", cdCorretora = "+cdCorretora+", representantes = "+representantes+", conta = "+conta+", dataAbertura = "+dataAbertura+", razaoSocial = "+razaoSocial+", cnpj = "+cnpj+", simplesNacional = "+simplesNacional+", email = "+email+", telefone = "+telefone+", login = "+login+", celular = "+celular+"]";
    }
}
