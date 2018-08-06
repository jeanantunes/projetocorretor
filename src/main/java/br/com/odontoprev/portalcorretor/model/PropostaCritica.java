package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portal.corretor.dto.VendaCritica;

import java.util.ArrayList;
import java.util.List;

public class PropostaCritica {

    private VendaCritica venda;
    private List<FichaFinanciera> fichaFinanciera;

    public VendaCritica getVenda() {
        return venda;
    }

    public void setVenda(VendaCritica venda) {
        this.venda = venda;
    }

    public List<FichaFinanciera> getFichaFinanciera() {
        if (fichaFinanciera == null) {
            fichaFinanciera = new ArrayList<>();
        }
        return fichaFinanciera;
    }

    public void setFichaFinanciera(List<FichaFinanciera> fichaFinanciera) {
        this.fichaFinanciera = fichaFinanciera;
    }

    @Override
    public String toString() {
        return "PropostaCriticaResponse ["
                + "venda=" + venda.toString()
                + "]";
    }
}
