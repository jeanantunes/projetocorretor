package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.Service.dto.PropostaResponse;

import java.util.List;

public class ListaPropostas {


    private List<PropostaResponse> propostaPF;
    private List<PropostaResponse> propostaPME;
    private Integer totalVidas;
    private Integer totalPF;
    private Integer totalPME;

    public List<PropostaResponse> getPropostaPF() {
        return propostaPF;
    }

    public void setPropostaPF(List<PropostaResponse> propostaPF) {
        this.propostaPF = propostaPF;
    }

    public List<PropostaResponse> getPropostaPME() {
        return propostaPME;
    }

    public void setPropostaPME(List<PropostaResponse> propostaPME) {
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
