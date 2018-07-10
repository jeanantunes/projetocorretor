package br.com.odontoprev.portalcorretor.model;

import java.util.ArrayList;
import java.util.List;

public class FichaFinanceiraResponse extends FichaFinanciera {

    public List<FichaFinanciera> getFichaFinanciera() {
        if (fichaFinanciera == null) {
            new ArrayList<>();
        }
        return fichaFinanciera;
    }

    public void setFichaFinanciera(List<FichaFinanciera> fichaFinanciera) {
        this.fichaFinanciera = fichaFinanciera;
    }

    private List<FichaFinanciera> fichaFinanciera;

    @Override
    public String toString() {
        return "FichaFinanceiraResponse{" +
                "fichaFinanciera=" + fichaFinanciera +
                '}';
    }
}

