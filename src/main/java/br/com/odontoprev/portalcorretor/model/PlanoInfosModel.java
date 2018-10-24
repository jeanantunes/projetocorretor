package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class PlanoInfosModel {

    List<PlanoInfo> pf;
    List<PlanoInfo> pme;

    public List<PlanoInfo> getPf() {
        return pf;
    }

    public void setPf(List<PlanoInfo> pf) {
        this.pf = pf;
    }

    public List<PlanoInfo> getPme() {
        return pme;
    }

    public void setPme(List<PlanoInfo> pme) {
        this.pme = pme;
    }
}
