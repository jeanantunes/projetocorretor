package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.Date;

public class Proposta {
    private long cdEmpresa;
    private String nome;
    private String statusVenda;
    private Date dataVenda;
    private String cnpj;
    private String tipoPlano;
    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

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
