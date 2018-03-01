package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.Service.dto.Proposta;

import java.util.List;

public class ListaPropostas {

    private List<Proposta> propostas;
    private List<Proposta> propostaPF;
    private List<Proposta> propostaPME;
    private Integer totalVidas;
    private Integer totalPF;
    private Integer totalPME;
    private Integer totalSucesso;
    private Integer totalCriticadas;
    private Integer percenteValorPF;
    private Integer percenteValorPME;
    private Integer total;
    private Double totalValorPME;
    private Double totalValorPF;
    private Integer countCorretoresAprovacao;

    public Integer getCountCorretoresAprovacao() {
        return countCorretoresAprovacao;
    }

    public void setCountCorretoresAprovacao(Integer countCorretoresAprovacao) {
        this.countCorretoresAprovacao = countCorretoresAprovacao;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public List<Proposta> getPropostaPF() {
        return propostaPF;
    }

    public void setPropostaPF(List<Proposta> propostaPF) {
        this.propostaPF = propostaPF;
    }

    public List<Proposta> getPropostaPME() {
        return propostaPME;
    }

    public void setPropostaPME(List<Proposta> propostaPME) {
        this.propostaPME = propostaPME;
    }

    public Integer getTotalVidas() {
        return totalVidas;
    }

    public void setTotalVidas(Integer totalVidas) {
        this.totalVidas = totalVidas;
    }

    public Integer getTotalPF() {
        return totalPF;
    }

    public void setTotalPF(Integer totalPF) {
        this.totalPF = totalPF;
    }

    public Integer getTotalPME() {
        return totalPME;
    }

    public void setTotalPME(Integer totalPME) {
        this.totalPME = totalPME;
    }

    public Integer getTotalSucesso() {
        return totalSucesso;
    }

    public void setTotalSucesso(Integer totalSucesso) {
        this.totalSucesso = totalSucesso;
    }

    public Integer getTotalCriticadas() {
        return totalCriticadas;
    }

    public void setTotalCriticadas(Integer totalCriticadas) {
        this.totalCriticadas = totalCriticadas;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getTotalValorPME() {
        return totalValorPME;
    }

    public void setTotalValorPME(Double totalValorPME) {
        this.totalValorPME = totalValorPME;
    }

    public Double getTotalValorPF() {
        return totalValorPF;
    }

    public void setTotalValorPF(Double totalValorPF) {
        this.totalValorPF = totalValorPF;
    }

    public Integer getPercenteValorPF() {
        return percenteValorPF;
    }

    public void setPercenteValorPF(Integer percenteValorPF) {
        this.percenteValorPF = percenteValorPF;
    }

    public Integer getPercenteValorPME() {
        return percenteValorPME;
    }

    public void setPercenteValorPME(Integer percenteValorPME) {
        this.percenteValorPME = percenteValorPME;
    }
}
