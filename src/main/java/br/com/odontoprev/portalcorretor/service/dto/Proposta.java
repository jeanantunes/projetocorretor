package br.com.odontoprev.portalcorretor.service.dto;

import java.util.List;

public class Proposta {

    private String cdVenda;
    private String cpf;
    private String propostaDcms;
    private String nome;
    private String nomeFantasia;
    private String statusVenda;
    private String atendimento;
    private String empresa;
    private String codOdonto;
    private String nrImportacao;
    private String forca;
    private String corretora;
    private String dsErroRegistro;
    private List<String> criticas;
    private String cnpj;
    private long cdEmpresa;
    private Double valor;


    private String dataVenda;
    private String tipoPlano;



    public Proposta() {
        super();

    }

    public Proposta(String nomeForca, String tipoVenda, String dataVenda, Double valorTotal) {
        this.nome = nomeForca;
        this.tipoPlano = tipoVenda;
        this.dataVenda = dataVenda;
        this.valor = valorTotal;
    }

    public String getForca() {
        return forca;
    }

    public void setForca(String forca) {
        this.forca = forca;
    }

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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
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

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(String cdVenda) {
        this.cdVenda = cdVenda;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPropostaDcms() {
        return propostaDcms;
    }

    public void setPropostaDcms(String propostaDcms) {
        this.propostaDcms = propostaDcms;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCodOdonto() {
        return codOdonto;
    }

    public void setCodOdonto(String codOdonto) {
        this.codOdonto = codOdonto;
    }

    public String getNrImportacao() {
        return nrImportacao;
    }

    public void setNrImportacao(String nrImportacao) {
        this.nrImportacao = nrImportacao;
    }

    public String getCorretora() {
        return corretora;
    }

    public void setCorretora(String corretora) {
        this.corretora = corretora;
    }

    public String getDsErroRegistro() {
        return dsErroRegistro;
    }

    public void setDsErroRegistro(String dsErroRegistro) {
        this.dsErroRegistro = dsErroRegistro;
    }

    public List<String> getCriticas() {
        return criticas;
    }

    public void setCriticas(List<String> criticas) {
        this.criticas = criticas;
    }
}
