package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.Date;

public class Corretora {

    public int getCdCorretora() {
        return cdCorretora;
    }

    public void setCdCorretora(int cdCorretora) {
        this.cdCorretora = cdCorretora;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatusCnpj() {
        return statusCnpj;
    }

    public void setStatusCnpj(Boolean statusCnpj) {
        this.statusCnpj = statusCnpj;
    }

    public Boolean getSimplesNacional() {
        return simplesNacional;
    }

    public void setSimplesNacional(Boolean simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Endereco getEnderecoCorretora() {
        return enderecoCorretora;
    }

    public void setEnderecoCorretora(Endereco enderecoCorretora) {
        this.enderecoCorretora = enderecoCorretora;
    }

    private int cdCorretora;
    private String cnpj;
    private String razaoSocial;
    private String cnae;
    private String telefone;
    private String celular;
    private String email;
    private Boolean statusCnpj;
    private Boolean simplesNacional;
    private Date dataAbertura;
    private Endereco enderecoCorretora;


}
