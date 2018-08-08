package br.com.odontoprev.portalcorretor.model;

import java.util.ArrayList;
import java.util.List;

public class PropostaCritica {

    private VendaCritica venda;
    private List<FichaFinanciera> fichaFinanciera;
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PropostaCriticaResponse ["
                + "venda=" + venda.toString()
                + "]";
    }
}
