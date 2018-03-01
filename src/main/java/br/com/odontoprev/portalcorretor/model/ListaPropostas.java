package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.Service.dto.dashboardPropostasPF;

import java.util.List;

public class ListaPropostas {


    private List<dashboardPropostasPF> propostaPF;
    private List<dashboardPropostasPF> propostaPME;
    private Integer totalVidas;
    private Integer totalPF;
    private Integer totalPME;

    public List<dashboardPropostasPF> getPropostaPF() {
        return propostaPF;
    }

    public void setPropostaPF(List<dashboardPropostasPF> propostaPF) {
        this.propostaPF = propostaPF;
    }

    public List<dashboardPropostasPF> getPropostaPME() {
        return propostaPME;
    }

    public void setPropostaPME(List<dashboardPropostasPF> propostaPME) {
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
}
