package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.List;

public class PropostaResponse {
    private List<dashboardPropostasPME> dashboardPropostasPME;

    public List<br.com.odontoprev.portalcorretor.Service.dto.dashboardPropostasPME> getDashboardPropostasPME() {
        return dashboardPropostasPME;
    }

    public void setDashboardPropostasPME(List<br.com.odontoprev.portalcorretor.Service.dto.dashboardPropostasPME> dashboardPropostasPME) {
        this.dashboardPropostasPME = dashboardPropostasPME;
    }
}
