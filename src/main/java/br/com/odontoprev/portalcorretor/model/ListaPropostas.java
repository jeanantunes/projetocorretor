package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class ListaPropostas {


    private List<Proposta> propostaPF;
    private List<Proposta> propostaPME;
    private Long totalVidas;
    private Long totalPF;
    private Long totalPME;

    public Long getTotalPF() {
        return totalPF;
    }

    public void setTotalPF(Long totalPF) {
        this.totalPF = totalPF;
    }

    public Long getTotalPME() {
        return totalPME;
    }

    public void setTotalPME(Long totalPME) {
        this.totalPME = totalPME;
    }

    public ListaPropostas() {
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

    public Long getTotalVidas() {
        return totalVidas;
    }

    public void setTotalVidas(Long totalVidas) {
        this.totalVidas = totalVidas;
    }
}
