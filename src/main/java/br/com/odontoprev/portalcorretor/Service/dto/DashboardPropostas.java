package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.ArrayList;
import java.util.List;

public class DashboardPropostas {

    private List<Proposta> dashboardPropostasPF = new ArrayList<>();

    private List<Proposta> dashboardPropostasPME = new ArrayList<>();

    public List<Proposta> getDashboardPropostasPF() {
        return dashboardPropostasPF;
    }

    public void setDashboardPropostasPF(List<Proposta> dashboardPropostasPF) {
        this.dashboardPropostasPF = dashboardPropostasPF;
    }

    public List<Proposta> getDashboardPropostasPME() {
        return dashboardPropostasPME;
    }

    public void setDashboardPropostasPME(List<Proposta> dashboardPropostasPME) {
        this.dashboardPropostasPME = dashboardPropostasPME;
    }
}
