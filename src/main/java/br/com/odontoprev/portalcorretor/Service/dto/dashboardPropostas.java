package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.Date;

public class dashboardPropostas {
    private long cdEmpresa;
    private String nome;
    private String statusVenda;
    private Date dataVenda;
    private String cnpj;

    public long getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(long cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public String getNomeCorretora() {
        return nome;
    }

    public void setNomeCorretora(String nomeCorretora) {
        this.nome = nomeCorretora;
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
