package com.vendaodonto.vendasodontoprev.requests.models;

/**
 * Created by sleke on 25/02/2018.
 */

public class ForcaVendaModelResponse
{
    private String nomeGerente;

    private String email;

    private String nomeEmpresa;

    private String rg;

    private String responsavel;

    private String nome;

    private String login;

    private String cpf;

    private String statusUsuario;

    private String senha;

    private String cargo;

    public String getNomeGerente ()
    {
        return nomeGerente;
    }

    public void setNomeGerente (String nomeGerente)
    {
        this.nomeGerente = nomeGerente;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getNomeEmpresa ()
    {
        return nomeEmpresa;
    }

    public void setNomeEmpresa (String nomeEmpresa)
    {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getRg ()
    {
        return rg;
    }

    public void setRg (String rg)
    {
        this.rg = rg;
    }

    public String getResponsavel ()
    {
        return responsavel;
    }

    public void setResponsavel (String responsavel)
    {
        this.responsavel = responsavel;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getLogin ()
    {
        return login;
    }

    public void setLogin (String login)
    {
        this.login = login;
    }

    public String getCpf ()
    {
        return cpf;
    }

    public void setCpf (String cpf)
    {
        this.cpf = cpf;
    }

    public String getStatusUsuario ()
    {
        return statusUsuario;
    }

    public void setStatusUsuario (String statusUsuario)
    {
        this.statusUsuario = statusUsuario;
    }

    public String getSenha ()
    {
        return senha;
    }

    public void setSenha (String senha)
    {
        this.senha = senha;
    }

    public String getCargo ()
    {
        return cargo;
    }

    public void setCargo (String cargo)
    {
        this.cargo = cargo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nomeGerente = "+nomeGerente+", email = "+email+", nomeEmpresa = "+nomeEmpresa+", rg = "+rg+", responsavel = "+responsavel+", nome = "+nome+", login = "+login+", cpf = "+cpf+", statusUsuario = "+statusUsuario+", senha = "+senha+", cargo = "+cargo+"]";
    }
}
