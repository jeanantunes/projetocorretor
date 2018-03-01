package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.Service.dto.Proposta;

import java.util.List;

public class ListaPropostas {


    private List<Proposta> propostaPF;
    private List<Proposta> propostaPME;
    private Integer totalVidas;
    private Integer totalPF;
    private Integer totalPME;


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
}
