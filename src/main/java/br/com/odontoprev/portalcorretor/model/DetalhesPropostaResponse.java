package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;
import java.util.List;

public class DetalhesPropostaResponse implements Serializable {

    private Long cdVenda;
    private VendaCritica venda;
    private List<Beneficiarios> dependentes;

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
}
