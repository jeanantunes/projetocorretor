package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.Date;

public class PropostaResponse {

    private long cdEmpresa;
    private String nome;
    private String statusVenda;
    private Date dataVenda;
    private String cnpj;


    public PropostaResponse(){}

    public PropostaResponse(long cdEmpresa, String nome, String statusVenda, Date dataVenda, String cnpj) {
        this.cdEmpresa = cdEmpresa;
        this.nome = nome;
        this.statusVenda = statusVenda;
        this.dataVenda = dataVenda;
        this.cnpj = cnpj;
    }

    public long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
