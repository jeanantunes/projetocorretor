package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.ArrayList;
import java.util.List;

public class DashboardPropostas {

    private List<Proposta> propostasPF = new ArrayList<>();

    private List<Proposta> propostasPME = new ArrayList<>();

    public List<Proposta> getPropostasPF() {
        return propostasPF;
    }

    public void setPropostasPF(List<Proposta> propostasPF) {
        this.propostasPF = propostasPF;
    }

    public List<Proposta> getDashboardPropostasPME() {
        return propostasPME;
    }

    public void setDashboardPropostasPME(List<Proposta> propostasPME) {
        this.propostasPME = propostasPME;
    }
}
