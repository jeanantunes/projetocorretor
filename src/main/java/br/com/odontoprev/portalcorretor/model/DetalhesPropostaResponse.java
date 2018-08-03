package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portal.corretor.dto.VendaCritica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetalhesPropostaResponse implements Serializable {

    private Long cdVenda;
    private VendaCritica venda;
    private List<Beneficiarios> dependentes;
    private List<FichaFinanciera> fichaFinanciera;
    private Long id;

    public Long getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(Long cdVenda) {
        this.cdVenda = cdVenda;
    }

    public VendaCritica getVenda() {
        return venda;
    }

    public void setVenda(VendaCritica venda) {
        this.venda = venda;
    }

    public List<Beneficiarios> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Beneficiarios> dependentes) {
        this.dependentes = dependentes;
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
}
