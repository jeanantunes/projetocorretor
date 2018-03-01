package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class DashboardCorretora {

    private List<String> vendedores;
    private List<String> tipoPlano;
    private List<Proposta> propostas;
    private Long countCorretoresAprovacao;
    private Long countCorretoresSucesso;
    private Long countCorretoresCriticadas;
    private Double valorPessoaFisica;
    private Double valorPME;

    public DashboardCorretora() {
    }

    public DashboardCorretora(Long countCorretoresAprovacao, Long countCorretoresSucesso, Long countCorretoresCriticadas, Double valorPessoaFisica, Double valorPME, List<String> vendedores, List<String> tipoPlano, List<Proposta> propostas) {
        this.countCorretoresAprovacao = countCorretoresAprovacao;
        this.countCorretoresSucesso = countCorretoresSucesso;
        this.countCorretoresCriticadas = countCorretoresCriticadas;
        this.valorPessoaFisica = valorPessoaFisica;
        this.valorPME = valorPME;
        this.vendedores = vendedores;
        this.tipoPlano = tipoPlano;
        this.propostas = propostas;
    }

    public Long getCountCorretoresAprovacao() {
        return countCorretoresAprovacao;
    }

    public void setCountCorretoresAprovacao(Long countCorretoresAprovacao) {
        this.countCorretoresAprovacao = countCorretoresAprovacao;
    }

    public Long getCountCorretoresSucesso() {
        return countCorretoresSucesso;
    }

    public void setCountCorretoresSucesso(Long countCorretoresSucesso) {
        this.countCorretoresSucesso = countCorretoresSucesso;
    }

    public Long getCountCorretoresCriticadas() {
        return countCorretoresCriticadas;
    }

    public void setCountCorretoresCriticadas(Long countCorretoresCriticadas) {
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
