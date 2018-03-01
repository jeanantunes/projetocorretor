package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.List;

public class dashboardPropostasPF {
    private List<dashboardPropostas> dashboardPropostasPF;

    public List<dashboardPropostas> getDashboardPropostas() {
        return dashboardPropostasPF;
    }

    public void setDashboardPropostas(List<dashboardPropostas> dashboardPropostas) {
        this.dashboardPropostasPF = dashboardPropostas;
    }
}
