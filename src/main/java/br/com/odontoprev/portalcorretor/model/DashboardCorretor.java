package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class DashboardCorretor {

    private int countCorretoresAprovacao;
    private int countCorretoresSucesso;
    private int countCorretoresCriticadas;

    private Double valorPessoaFisica;
    private Double valorPME;

    List<String> vendedores;
    List<String> tipoPlano;
    List<Proposta> propostas;

    public int getCountCorretoresAprovacao() {
        return countCorretoresAprovacao;
    }

    public void setCountCorretoresAprovacao(int countCorretoresAprovacao) {
        this.countCorretoresAprovacao = countCorretoresAprovacao;
    }

    public int getCountCorretoresSucesso() {
        return countCorretoresSucesso;
    }

    public void setCountCorretoresSucesso(int countCorretoresSucesso) {
        this.countCorretoresSucesso = countCorretoresSucesso;
    }

    public int getCountCorretoresCriticadas() {
        return countCorretoresCriticadas;
    }

    public void setCountCorretoresCriticadas(int countCorretoresCriticadas) {
        this.countCorretoresCriticadas = countCorretoresCriticadas;
    }

    public Double getValorPessoaFisica() {
        return valorPessoaFisica;
    }

    public void setValorPessoaFisica(Double valorPessoaFisica) {
        this.valorPessoaFisica = valorPessoaFisica;
    }

    public Double getValorPME() {
        return valorPME;
    }

    public void setValorPME(Double valorPME) {
        this.valorPME = valorPME;
    }

    public List<String> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<String> vendedores) {
        this.vendedores = vendedores;
    }

    public List<String> getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(List<String> tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

}



